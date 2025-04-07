package br.com.alexgirao.pedido.controller;

import br.com.alexgirao.pedido.controller.dto.ApiResponse;
import br.com.alexgirao.pedido.controller.dto.PaginationResponse;
import br.com.alexgirao.pedido.controller.dto.PedidoResponse;
import br.com.alexgirao.pedido.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/clientes/{clienteId}/pedidos")
    public ResponseEntity<ApiResponse<PedidoResponse>> listOrders(@PathVariable("clienteId") Long clienteId,
                                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(name = "size", defaultValue = "10") Integer size){

        var pageResponse = pedidoService.findAllByClienteId(clienteId, PageRequest.of(page, size));
        var totalPedidos = pedidoService.findTotalPedidosByClienteId(clienteId);

        return ResponseEntity.ok(new ApiResponse<>(Map.of("totalPedidos", totalPedidos),
            pageResponse.getContent(),
            PaginationResponse.fromPage(pageResponse))
        );
    }


}
