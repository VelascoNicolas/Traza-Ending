package com.entidades.buenSabor;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Imp.ArticuloServiceImpl;
import com.entidades.buenSabor.business.service.Imp.PedidoServiceImpl;
import com.entidades.buenSabor.business.service.Imp.PromocionServiceImpl;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.domain.enums.*;
import com.entidades.buenSabor.repositories.*;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class BuenSaborApplication {
	private static final Logger logger = LoggerFactory.getLogger(BuenSaborApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BuenSaborApplication.class, args);
		logger.info("Estoy activo en el main Alberto");
	}

	@Autowired
	private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

	@Bean
	@Transactional
	CommandLineRunner init(ClienteRepository clienteRepository,
						   StockInsumoSucursalRepository stockInsumoSucursalRepository,
						   PaisRepository paisRepository,
						   ProvinciaRepository provinciaRepository,
						   LocalidadRepository localidadRepository,
						   EmpresaRepository empresaRepository,
						   SucursalRepository sucursalRepository,
						   DomicilioRepository domicilioRepository,
						   UnidadMedidaRepository unidadMedidaRepository,
						   CategoriaRepository categoriaRepository,
						   ArticuloInsumoRepository articuloInsumoRepository,
						   ArticuloManufacturadoRepository articuloManufacturadoRepository,
						   PromocionRepository promocionRepository,
						   PedidoRepository pedidoRepository,
						   EmpleadoRepository empleadoRepository, FacturaRepository facturaRepository) {
		return args -> {
			logger.info("----------------ESTOY----FUNCIONANDO---------------------");
			RestTemplate restTemplate = new RestTemplate();
			String jsonResponse = restTemplate.getForObject("https://infra.datos.gob.ar/georef/departamentos.json", String.class);
			JSONObject jsonObject = new JSONObject(jsonResponse);
			JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");

			// Obtener el país
			Pais pais = paisRepository.findById(1L).orElseGet(() -> {
				Pais newPais = new Pais();
				newPais.setId(1L);
				newPais.setEliminado(false);
				newPais.setNombre("Argentina");
				return paisRepository.save(newPais);
			});

			departamentosArray.forEach(obj -> {
				JSONObject departamentoJson = (JSONObject) obj;

				Long localidadId = Long.parseLong(departamentoJson.getString("id"));
				String localidadNombre = departamentoJson.getString("nombre");

				JSONObject provinciaJson = departamentoJson.getJSONObject("provincia");
				Long provinciaId = Long.parseLong(provinciaJson.getString("id"));
				String provinciaNombre = provinciaJson.getString("nombre");

				// Verificar si la provincia ya existe por nombre, si no, crearla y guardarla
				Provincia provincia = provinciaRepository.findByNombre(provinciaNombre);
				if (provincia == null) {
					provincia = new Provincia();
					provincia.setId(provinciaId);
					provincia.setNombre(provinciaNombre);
					provincia.setPais(pais);
					provincia = provinciaRepository.save(provincia);
				}

				Localidad localidad = new Localidad();
				localidad.setId(localidadId);
				localidad.setNombre(localidadNombre);
				localidad.setProvincia(provincia);
				localidadRepository.save(localidad);
			});
			final LocalDate fecha = LocalDate.of(9999,12,31);
			final LocalDate fechaActual = LocalDate.now();
			// Etapa del dashboard
			// Crear 1 pais
			// Crear 2 provincias para ese pais
			// crear 2 localidades para cada provincia
			//CREACION DE PROVINCIAS
			Provincia provincia2 = provinciaRepository.getById(1L);

			//CREACION DE LOCALIDADES
			Localidad localidad1 = localidadRepository.getById(158L);
			Localidad localidad2 = localidadRepository.getById(371L);
			Localidad localidad3 = Localidad.builder().fechaBaja(fecha).nombre("Mar del Plata").provincia(provincia2).build();
			Localidad localidad4 = Localidad.builder().fechaBaja(fecha).nombre("Mar de las Pampas").provincia(provincia2).build();

			localidadRepository.save(localidad3);
			localidadRepository.save(localidad4);

			// Crear 1 empresa, 2 sucursales para esa empresa y los Domicilios para esas sucursales

			Empresa empresaCarlos = Empresa.builder().fechaBaja(fecha).nombre("Lo de Carlos").cuil(30546780L).razonSocial("Venta de Alimentos").build();
			empresaRepository.save(empresaCarlos);

			Sucursal sucursalGuaymallen = Sucursal.builder().fechaBaja(fecha).
					nombre("En Guaymallen").
					build();

			Sucursal sucursalMarDelPlata = Sucursal.builder().fechaBaja(fecha).nombre("En MDQ").build();

			Domicilio domicilioBerutti = Domicilio.builder().fechaBaja(fecha).cp(5519).calle("Berutti").numero(2684).piso(0).nroDpto(5).
					localidad(localidad2).build();

			Domicilio domicilioGaboto = Domicilio.builder().fechaBaja(fecha).cp(7600).calle("Gaboto").piso(0).nroDpto(0).numero(3475).
					localidad(localidad3).build();

			//ASOCIAMOS LOS DOMICILIOS A SUCURSAL
			sucursalGuaymallen.setDomicilio(domicilioBerutti);
			sucursalMarDelPlata.setDomicilio(domicilioGaboto);

			//ASOCIAMOS SUCURSALES A EMPRESA
			empresaCarlos.getSucursales().add(sucursalGuaymallen);
			empresaCarlos.getSucursales().add(sucursalMarDelPlata);

			//ASIGNAMOS EMPRESA A SUCURSALES
			sucursalGuaymallen.setEmpresa(empresaCarlos);
			sucursalMarDelPlata.setEmpresa(empresaCarlos);
			// Grabo las sucursales
			sucursalRepository.save(sucursalGuaymallen);
			sucursalRepository.save(sucursalMarDelPlata);
			// Grabo empresa
			empresaRepository.save(empresaCarlos);

			// Crear Categorías de productos y subCategorías de los mismos
			Categoria categoriaBebidas = Categoria.builder().fechaBaja(fecha).denominacion("Bebidas").
					build();
			categoriaRepository.save(categoriaBebidas);

			Categoria categoriaGaseosas = Categoria.builder().fechaBaja(fecha).denominacion("Gaseosas").
					build();
			categoriaRepository.save(categoriaGaseosas);

			Categoria categoriaTragos = Categoria.builder().fechaBaja(fecha).denominacion("Tragos").
					build();
			categoriaRepository.save(categoriaTragos);

			Categoria categoriaPizzas = Categoria.builder().fechaBaja(fecha).denominacion("Pizzas").
					build();

			Categoria categoriaInsumos = Categoria.builder().fechaBaja(fecha).denominacion("Insumos").
					build();

			// Grabo la categoría de insumos y de Manufacturados
			categoriaRepository.save(categoriaPizzas);
			categoriaRepository.save(categoriaInsumos);
			// Asigno subCategorías

			categoriaBebidas.getSubCategorias().add(categoriaGaseosas);
			categoriaBebidas.getSubCategorias().add(categoriaTragos);
			// Grabo las Subcategorías
			categoriaRepository.save(categoriaBebidas);

			logger.info("---------------voy a asignar a Guaymallen--------------------");
			//ASOCIAMOS CATEGORIAS CON SUCURSAL
			categoriaInsumos.getSucursales().add(sucursalGuaymallen);
			// Cargo las categorias a la sucursal guaymallen
			sucursalGuaymallen.getCategorias().add(categoriaInsumos);
			sucursalGuaymallen.getCategorias().add(categoriaBebidas);
			sucursalGuaymallen.getCategorias().add(categoriaGaseosas);
			sucursalGuaymallen.getCategorias().add(categoriaTragos);
			sucursalGuaymallen.getCategorias().add(categoriaPizzas);
			logger.info("{}",sucursalGuaymallen);
			// Grabo las categorias que vende esa sucursal
			sucursalRepository.save(sucursalGuaymallen);

			logger.info("---------------saque el save de abajo-------------------");


			logger.info("---------------grabe guaymallen--------------------");

			logger.info("---------------voy a asignar a Mardel Plata--------------------");
			categoriaInsumos.getSucursales().add(sucursalMarDelPlata);
			// Cargo las categorias a la sucursal Mardel Plata
			sucursalMarDelPlata.getCategorias().add(categoriaInsumos);
			sucursalMarDelPlata.getCategorias().add(categoriaBebidas);
			sucursalMarDelPlata.getCategorias().add(categoriaGaseosas);
			sucursalMarDelPlata.getCategorias().add(categoriaTragos);
			sucursalMarDelPlata.getCategorias().add(categoriaPizzas);
			// Grabo las categorias que vende esa sucursal
			sucursalRepository.save(sucursalMarDelPlata);

			logger.info("---------------grabe Mardel Plata--------------------");




			// Crear Unidades de medida
			UnidadMedida unidadMedidaLitros = UnidadMedida.builder().fechaBaja(fecha).denominacion("Litros").build();
			UnidadMedida unidadMedidaKilos = UnidadMedida.builder().fechaBaja(fecha).denominacion("Kilogramos").build();
			UnidadMedida unidadMedidaCantidad = UnidadMedida.builder().fechaBaja(fecha).denominacion("Unidades").build();
			UnidadMedida unidadMedidaPorciones = UnidadMedida.builder().fechaBaja(fecha).denominacion("Porciones").build();
			unidadMedidaRepository.save(unidadMedidaLitros);
			unidadMedidaRepository.save(unidadMedidaKilos);
			unidadMedidaRepository.save(unidadMedidaCantidad);
			unidadMedidaRepository.save(unidadMedidaPorciones);



			// Crear Insumos , coca cola , harina , etc
			ArticuloInsumo cocaCola = ArticuloInsumo.builder().fechaBaja(fecha).
					denominacion("Coca-Cola 1.5L").
					unidadMedida(unidadMedidaCantidad).
					esParaElaborar(false).
					categoria(categoriaGaseosas).
					precioCompra(50.0).
					precioVenta(70.0).
					build();
			ArticuloInsumo harina = ArticuloInsumo.builder().categoria(categoriaInsumos).fechaBaja(fecha).denominacion("Harina").unidadMedida(unidadMedidaKilos).esParaElaborar(true).precioCompra(40.0).precioVenta(60.5).build();
			ArticuloInsumo queso = ArticuloInsumo.builder().categoria(categoriaInsumos).fechaBaja(fecha).denominacion("Queso").unidadMedida(unidadMedidaKilos).esParaElaborar(true).precioCompra(23.6).precioVenta(66.6).build();
			ArticuloInsumo tomate = ArticuloInsumo.builder().categoria(categoriaInsumos).fechaBaja(fecha).denominacion("Tomate").unidadMedida(unidadMedidaCantidad).esParaElaborar(true).precioCompra(23.6).precioVenta(66.6).build();

			// crear fotos para cada insumo
			ImagenArticulo imagenArticuloCoca = ImagenArticulo.builder().fechaBaja(fecha).
					url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").
					build();
			ImagenArticulo imagenArticuloHarina = ImagenArticulo.builder().fechaBaja(fecha).url("https://mandolina.co/wp-content/uploads/2023/03/648366622-1024x683.jpg").build();
			ImagenArticulo imagenArticuloQueso = ImagenArticulo.builder().fechaBaja(fecha).url("https://superdepaso.com.ar/wp-content/uploads/2021/06/SANTAROSA-PATEGRAS-04.jpg").build();
			ImagenArticulo imagenArticuloTomate = ImagenArticulo.builder().fechaBaja(fecha).url("https://thefoodtech.com/wp-content/uploads/2020/06/Componentes-de-calidad-en-el-tomate-828x548.jpg").build();

			//ASOCIAMOS IMAGEN CON INSUMOS
			cocaCola.getImagenes().add(imagenArticuloCoca);
			harina.getImagenes().add(imagenArticuloHarina);
			queso.getImagenes().add(imagenArticuloQueso);
			tomate.getImagenes().add(imagenArticuloTomate);
			// Grabamos los Articulos
			articuloInsumoRepository.save(cocaCola);
			articuloInsumoRepository.save(harina);
			articuloInsumoRepository.save(queso);
			articuloInsumoRepository.save(tomate);

			StockInsumoSucursal s1 = StockInsumoSucursal.builder().stockActual(25.0).stockMinimo(1.0).stockMaximo(50.0).articuloInsumo(cocaCola).sucursal(sucursalMarDelPlata).build();
			StockInsumoSucursal s2 = StockInsumoSucursal.builder().stockActual(40.0).stockMinimo(10.0).stockMaximo(50.0).articuloInsumo(harina).sucursal(sucursalMarDelPlata).build();
			StockInsumoSucursal s3 = StockInsumoSucursal.builder().stockActual(20.0).stockMinimo(5.0).stockMaximo(50.0).articuloInsumo(queso).sucursal(sucursalMarDelPlata).build();
			StockInsumoSucursal s4 = StockInsumoSucursal.builder().stockActual(20.0).stockMinimo(5.0).stockMaximo(50.0).articuloInsumo(tomate).sucursal(sucursalMarDelPlata).build();
			StockInsumoSucursal s5 = StockInsumoSucursal.builder().stockActual(25.0).stockMinimo(1.0).stockMaximo(50.0).articuloInsumo(cocaCola).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal s6 = StockInsumoSucursal.builder().stockActual(40.0).stockMinimo(10.0).stockMaximo(50.0).articuloInsumo(harina).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal s7 = StockInsumoSucursal.builder().stockActual(20.0).stockMinimo(5.0).stockMaximo(50.0).articuloInsumo(queso).sucursal(sucursalGuaymallen).build();
			StockInsumoSucursal s8 = StockInsumoSucursal.builder().stockActual(20.0).stockMinimo(5.0).stockMaximo(50.0).articuloInsumo(tomate).sucursal(sucursalGuaymallen).build();
			stockInsumoSucursalRepository.save(s1);
			stockInsumoSucursalRepository.save(s2);
			stockInsumoSucursalRepository.save(s3);
			stockInsumoSucursalRepository.save(s4);
			stockInsumoSucursalRepository.save(s5);
			stockInsumoSucursalRepository.save(s6);
			stockInsumoSucursalRepository.save(s7);
			stockInsumoSucursalRepository.save(s8);

			// Crear Articulos Manufacturados
			ArticuloManufacturado pizzaMuzarella = ArticuloManufacturado.builder().fechaBaja(fecha).
					denominacion("Pizza Muzarella").
					descripcion("Una pizza clasica").
					categoria(categoriaPizzas).
					unidadMedida(unidadMedidaPorciones).
					precioVenta(130.0).
					tiempoEstimadoMinutos(16).
					preparacion("Pasos de preparacion de una muzza de toda la vida").
					build();
			ArticuloManufacturado pizzaNapolitana = ArticuloManufacturado.builder().categoria(categoriaPizzas).fechaBaja(fecha).denominacion("Pizza Napolitana").descripcion("Una pizza clasica").unidadMedida(unidadMedidaPorciones).precioVenta(150.0).tiempoEstimadoMinutos(16).preparacion("Pasos de preparacion de una pizza napolitana italiana").build();

			// Crear fotos para los artículos manufacturados
			ImagenArticulo imagenArticuloPizzaMuzarella = ImagenArticulo.builder().fechaBaja(fecha).
					url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").
					build();
			ImagenArticulo imagenArticuloPizzaNapolitana = ImagenArticulo.builder().fechaBaja(fecha).url("https://assets.elgourmet.com/wp-content/uploads/2023/03/8metlvp345_portada-pizza-1024x686.jpg.webp").build();

			//ASOCIAMOS IMAGEN CON ARTICULO MANUFACTURADO
			pizzaMuzarella.getImagenes().add(imagenArticuloPizzaMuzarella);
			pizzaNapolitana.getImagenes().add(imagenArticuloPizzaNapolitana);
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			//Agregar a la sucursal los articulos manufacturados
			Sucursal sucursalID1 = sucursalRepository.findWithArticulosById(1L);
			Sucursal sucursalID2 = sucursalRepository.findWithArticulosById(2L);
			ArticuloManufacturado articuloId1 = articuloManufacturadoRepository.findAllWithSucursales(5L);
			ArticuloManufacturado articuloId2 = articuloManufacturadoRepository.findAllWithSucursales(6L);
			sucursalID1.getManufacturados().add(articuloId1);
			sucursalID1.getManufacturados().add(articuloId2);
			sucursalID2.getManufacturados().add(articuloId1);
			sucursalID2.getManufacturados().add(articuloId2);
			articuloId1.getSucursales().add(sucursalID1);
			articuloId2.getSucursales().add(sucursalID2);
			sucursalRepository.save(sucursalID1);
			sucursalRepository.save(sucursalID2);
			articuloManufacturadoRepository.save(articuloId1);
			articuloManufacturadoRepository.save(articuloId2);


			// Establecer las relaciones entre estos objetos Articulos de la Receta independiente
			ArticuloManufacturadoDetalle detalle1 = ArticuloManufacturadoDetalle.builder().fechaBaja(fecha).
					articuloInsumo(harina).
					cantidad(0.3).
					build();
			ArticuloManufacturadoDetalle detalle2 = ArticuloManufacturadoDetalle.builder().fechaBaja(fecha).articuloInsumo(queso).cantidad(0.6).build();
			ArticuloManufacturadoDetalle detalle3 = ArticuloManufacturadoDetalle.builder().fechaBaja(fecha).articuloInsumo(harina).cantidad(0.3).build();
			ArticuloManufacturadoDetalle detalle4 = ArticuloManufacturadoDetalle.builder().fechaBaja(fecha).articuloInsumo(queso).cantidad(0.65).build();
			ArticuloManufacturadoDetalle detalle5 = ArticuloManufacturadoDetalle.builder().fechaBaja(fecha).articuloInsumo(tomate).cantidad(2.0).build();
			// grabamos el Artículo Manufacturado
			articuloManufacturadoDetalleRepository.save(detalle1);
			articuloManufacturadoDetalleRepository.save(detalle2);
			articuloManufacturadoDetalleRepository.save(detalle3);
			articuloManufacturadoDetalleRepository.save(detalle4);
			articuloManufacturadoDetalleRepository.save(detalle5);

			//ASOCIAMOS LOS DETALLE MANUFACTURADO AL ARTICULO MANUFACTURADO - LA RECETA
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle1);
			pizzaMuzarella.getArticuloManufacturadoDetalles().add(detalle2);

			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle3);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle4);
			pizzaNapolitana.getArticuloManufacturadoDetalles().add(detalle5);
			// GRABAMOS LA RECETA
			articuloManufacturadoRepository.save(pizzaMuzarella);
			articuloManufacturadoRepository.save(pizzaNapolitana);

			// Establecer relaciones de las categorias - Cada Producto Manufacturado Pertenece a una categoria

			categoriaPizzas.addBoth(pizzaMuzarella);
			categoriaPizzas.addBoth(pizzaNapolitana);
			// Graba la categoria y los productos asociados
			categoriaRepository.save(categoriaPizzas);

			//	categoriaRepository.save(categoriaGaseosas); CREO QUE ESTA DE MAS REVISAR
			ImagenPromocion imagenPromocion = ImagenPromocion.builder().fechaBaja(fecha).url("https://i.pinimg.com/564x/84/21/a9/8421a9678cc165634a773429390a7343.jpg").build();
			ImagenPromocion imagenPromocion2 = ImagenPromocion.builder().fechaBaja(fecha).url("https://i.pinimg.com/564x/d3/c8/f6/d3c8f6bd742b1363337bd43e096cc5eb.jpg").build();

			Set<ImagenPromocion> imagenesPromo = new HashSet<>();
			imagenesPromo.add(imagenPromocion);
			imagenesPromo.add(imagenPromocion2);

			ImagenPromocion imagenPromocion3 = ImagenPromocion.builder().fechaBaja(fecha).url("https://storage.googleapis.com/fitia-api-bucket/media/images/recipe_images/1002846.jpg").build();
			ImagenPromocion imagenPromocion4 = ImagenPromocion.builder().fechaBaja(fecha).url("https://m.media-amazon.com/images/I/51v8nyxSOYL._SL1500_.jpg").build();

			Set<ImagenPromocion> imagenesPromo2 = new HashSet<>();
			imagenesPromo2.add(imagenPromocion3);
			imagenesPromo2.add(imagenPromocion4);


			// Crear promocion para sucursal - Dia de los enamorados
			// Tener en cuenta que esa promocion es exclusivamente para una sucursal determinada d euna empresa determinada
			Promocion promocionDiaEnamorados = Promocion.builder().fechaBaja(fecha).denominacion("Dia de los Enamorados")
					.imagenes(imagenesPromo)
					.fechaDesde(LocalDate.of(2024,2,13))
					.fechaHasta(LocalDate.of(2024,2,15))
					.horaDesde(LocalTime.of(0,0))
					.horaHasta(LocalTime.of(23,59))
					.descripcionDescuento("El descuento que se hace por san valentin, un dia antes y un dia despues")
					.precioPromocional(100.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();
			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo1=PromocionDetalle.builder().fechaBaja(fecha).cantidad(2).articulo(pizzaNapolitana).build();

			PromocionDetalle detallePromo2=PromocionDetalle.builder().fechaBaja(fecha).cantidad(1).articulo(cocaCola).build();
			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo1);
			promocionDiaEnamorados.getPromocionDetalles().add(detallePromo2);

			promocionRepository.save(promocionDiaEnamorados);

			Promocion pizzaConCoca = Promocion.builder().fechaBaja(fecha).denominacion("Pizza + coca")
					.fechaDesde(LocalDate.of(2024,2,13))
					.imagenes(imagenesPromo2)
					.fechaHasta(LocalDate.of(2024,2,15))
					.horaDesde(LocalTime.of(0,0))
					.horaHasta(LocalTime.of(23,59))
					.descripcionDescuento("Pizza + Coca Cola 1.5lts")
					.precioPromocional(100.0)
					.tipoPromocion(TipoPromocion.PROMOCION)
					.build();
			// Agregamos los Manufacturados y alguna bebida que figura como insumo
			PromocionDetalle detallePromo3=PromocionDetalle.builder().fechaBaja(fecha).cantidad(1).articulo(pizzaNapolitana).build();
			PromocionDetalle detallePromo4=PromocionDetalle.builder().fechaBaja(fecha).cantidad(2).articulo(cocaCola).build();

			pizzaConCoca.getPromocionDetalles().add(detallePromo3);
			pizzaConCoca.getPromocionDetalles().add(detallePromo4);

			promocionRepository.save(pizzaConCoca);

// revisar PARA QUE GRABE EL DETALLE D ELA PROMOCION
//-------------- ACA HAY QUE HARCODEAR PARA TRAER POR ID CADA SUCURSAL
// La sucursal buscada, luego debe salvarse nuevamente, pero ahora ya existe es como un Updete
// Peimero la busco y luego la grabo

			//sucursalRepository.findById();
//--------------------- ESTOS SAVE SE HACIAN NUEVAMENTE CON LA INSTANCIA ANTERIOR
//  Por eso daba duplicado, revisar la logica de esta parte
			// Sucursal Guaymallee
			Sucursal sucursalId1 = sucursalRepository.findWithPromocionesById(1L);
			Sucursal sucursalId2 = sucursalRepository.findWithPromocionesById(2L);
			Promocion promocionId1 = promocionRepository.findAllWithSucursales(1L);
			Promocion promocionId2 = promocionRepository.findAllWithSucursales(2L);
			sucursalId1.getPromociones().add(promocionId1);
			sucursalId1.getPromociones().add(promocionId2);
			sucursalId2.getPromociones().add(promocionId1);
			sucursalId2.getPromociones().add(promocionId2);
			promocionId1.getSucursales().add(sucursalId1);
			promocionId1.getSucursales().add(sucursalId2);
			sucursalRepository.save(sucursalId1);
			sucursalRepository.save(sucursalId2);
			promocionRepository.save(promocionId1);
			promocionRepository.save(promocionId2);
			logger.info("---------------Promociones en sucursal id = 1---------------");
			sucursalId1.getPromociones()
					.stream()
					.map(Promocion::getDenominacion)
					.forEach(logger::info);
			logger.info("---------------Sucursales con la promocion id = 1---------------");
			promocionId1.getSucursales()
					.stream()
					.map(Sucursal::getNombre)
					.forEach(logger::info);
			logger.info("----------------------------------------------------------------");
			//sucursalRepository.save(sucursalGuaymallen);
			//	sucursalRepository.save(sucursalMarDelPlata);


//			sucursalRepository.guardarSucursalConValidacion(sucursalGuaymallen);

//			sucursalRepository.guardarSucursalConValidacion(sucursalMarDelPlata);

			//Crea un cliente y un usuario
			ImagenCliente imagenCliente = ImagenCliente.builder().fechaBaja(fecha).url("https://hips.hearstapps.com/hmg-prod/images/la-la-land-final-1638446140.jpg").build();
			ImagenCliente imagenCliente2 = ImagenCliente.builder().fechaBaja(fecha).url("").build();
			Domicilio domicilioCliente = Domicilio.builder().fechaBaja(fecha).cp(5519).calle("Cangallo").numero(800).piso(0).nroDpto(1).localidad(localidad1).build();
			Domicilio domicilioCliente2 = Domicilio.builder().fechaBaja(fecha).cp(5507).calle("San Martin").numero(457).piso(0).nroDpto(1).localidad(localidad1).build();
			domicilioRepository.save(domicilioCliente);
			domicilioRepository.save(domicilioCliente2);

			Cliente cliente = new Cliente();

			cliente.setFechaBaja(fecha);
			cliente.setEliminado(false);
			cliente.setImagenCliente(imagenCliente);
			cliente.setUserName("velasconico003@gmail.com");
			cliente.setNombre("Juan");
			cliente.setApellido("Cutri");
			cliente.setTelefono("2615920825");
			cliente.getDomicilios().add(domicilioCliente);
			clienteRepository.save(cliente);

			Cliente cliente2 = new Cliente();

			cliente2.setFechaBaja(fecha);
			cliente2.setEliminado(false);
			cliente2.setImagenCliente(imagenCliente2);
			cliente2.setUserName("cliente@cliente.com");
			cliente2.setNombre("Tomas");
			cliente2.setApellido("Ianchina");
			cliente2.setTelefono("261 720-1161");
			cliente2.getDomicilios().add(domicilioCliente2);
			clienteRepository.save(cliente2);

			Empleado empleado=new Empleado();
			LocalDate date = LocalDate.of(1996,06,30);

			empleado.setFechaBaja(fecha);
			empleado.setActivo(true);
			empleado.setEmail("admin@admin.com");
			empleado.setNombre("Nicolas");
			empleado.setApellido("Velasco");
			empleado.setTelefono("2616579672");
			empleado.setFechaNacimiento(date);
			empleado.setRol(Rol.COCINERO);
			empleado.setSucursal(sucursalGuaymallen);
			sucursalGuaymallen.getEmpleados().add(empleado);
			empleadoRepository.save(empleado);
			logger.info("Empleado{}:",empleado);

			Empleado empleado1 =new Empleado();
			LocalDate date2 = LocalDate.of(1996,9,30);

			empleado1.setFechaBaja(fecha);
			empleado1.setActivo(true);
			empleado1.setEmail("cocinero@cocinero.com");
			empleado1.setNombre("COCINERO");
			empleado1.setApellido("2");
			empleado1.setTelefono("2612612612");
			empleado1.setFechaNacimiento(date2);
			empleado1.setRol(Rol.COCINERO);
			empleado1.setSucursal(sucursalGuaymallen);
			sucursalGuaymallen.getEmpleados().add(empleado1);
			empleadoRepository.save(empleado1);
			logger.info("Empleado{}:",empleado1);

			//Crea un pedido para el cliente
			PedidoServiceImpl pedidoService = new PedidoServiceImpl();
			PromocionService promocionService = new PromocionServiceImpl();
			ArticuloService articuloService = new ArticuloServiceImpl();
			Pedido save = new Pedido();
			save.setFechaPedido(fechaActual);
			save.setEliminado(false);
			save.setEstado(Estado.APROBADO);
			save.setTipoEnvio(TipoEnvio.TAKE_AWAY);
			save.setFormaPago(FormaPago.EFECTIVO);
			save.setDomicilio(domicilioCliente);
			save.setSucursal(sucursalMarDelPlata);
			save.setFactura(null);
			save.setCliente(cliente);
			save.setEmpleado(empleado);
			save.setTiempoDeEspera(LocalTime.of(16,30));
			
			DetallePedido detallePedido = new DetallePedido();
			detallePedido.setEliminado(false);
			detallePedido.setFechaBaja(fecha);
			detallePedido.setCantidad(2);
			detallePedido.setSubTotal(140.0);
			detallePedido.setArticulo(cocaCola);
			detallePedido.setPromocion(null);
			save.getDetallePedidos().add(detallePedido);
			save.calcularPrecioVentaTotal(200.0);
			save.calcularPrecioCostoTotal();
			pedidoRepository.save(save);

			Factura factura = new Factura();
			factura.setFechaFacturacion(save.getFechaPedido());
			factura.setFormaPago(save.getFormaPago());
			factura.setSubTotal(save.getTotal());
			if (save.getTipoEnvio() != TipoEnvio.DELIVERY) {
				factura.setDescuento(save.getTotal() * 0.1);
			} else {
				factura.setDescuento(0.0);
			}
			factura.setTotal(factura.getSubTotal() - factura.getDescuento());
			save.setFactura(factura);
			save.setEmpleado(empleado);
			save.setEstado(Estado.FACTURADO);

			pedidoRepository.save(save);
		};
	}
}
