package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMP;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class MercadoPagoController {
    public PreferenceMP getPreferenciaMercadoPago(Pedido pedido){
        try{
            //TOKEN DE TESTING
            MercadoPagoConfig.setAccessToken("APP_USR-1054800052942896-052114-3cefde6b07c182a749402469375d0aab-1821353489");

            //CREAR EL OBJETO DE COMPRA
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(pedido.getId()))
                    .title("Pedido El Buen Sabor")
                    .description("Pedido El Buen Sabor")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(String.valueOf(pedido.getTotal())))
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            //MAPEAR URL DE ESTADO
            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder()
                    .success("http://localhost:5173/cliente/mpExito/" + pedido.getId())
                    .pending("http://localhost:5173/cliente/mpPending/" + pedido.getId())
                    .failure("http://localhost:5173/cliente/mpError/" + pedido.getId())
                    .build();

            //EXCLUIR PAGO EN EFECTIVO POR MERCADO PAGO
            List<PreferencePaymentMethodRequest> excludedPaymentMethods=new ArrayList<>();
            List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
            excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

            PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                    .excludedPaymentMethods(excludedPaymentMethods)
                    .excludedPaymentTypes(excludedPaymentTypes)
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .paymentMethods(paymentMethods)
                    .build();
            //CREAR EL CLIENTE
            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            //CREAR LA PREFERENCIA DE MERCADO PAGO
            PreferenceMP mpPreference = new PreferenceMP();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());

            mpPreference.setId(preference.getId());
            return mpPreference;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}