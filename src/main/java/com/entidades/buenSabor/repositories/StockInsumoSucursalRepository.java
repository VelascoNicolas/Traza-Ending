package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.StockInsumoSucursal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockInsumoSucursalRepository extends BaseRepository<StockInsumoSucursal, Integer> {

    @Query(value = "SELECT * FROM STOCK_INSUMO_SUCURSAL WHERE SUCURSAL =?1 AND ARTICULO_INSUMO =?2", nativeQuery = true)
    StockInsumoSucursal getStockInsumoSucursalBySucursalAndInsumo(Long sucursalId, Long insumoId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE STOCK_INSUMO_SUCURSAL SET ELIMINADO = TRUE WHERE ARTICULO_INSUMO =?1", nativeQuery = true)
    void deleteLogico(Long insumoId);
}
