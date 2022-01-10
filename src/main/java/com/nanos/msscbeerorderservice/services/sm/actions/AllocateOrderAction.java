package com.nanos.msscbeerorderservice.services.sm.actions;

import com.nanos.msscbeerorderservice.config.JmsConfig;
import com.nanos.msscbeerorderservice.domain.BeerOrder;
import com.nanos.msscbeerorderservice.domain.BeerOrderEventEnum;
import com.nanos.msscbeerorderservice.domain.BeerOrderStatusEnum;
import com.nanos.msscbeerorderservice.repositories.BeerOrderRepository;
import com.nanos.msscbeerorderservice.services.BeerOrderManagerImpl;
import com.nanos.msscbeerorderservice.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE,
                beerOrderMapper.beerOrderToDto(beerOrder));

        log.debug("Sent Allocation Request for order id: " + beerOrderId);
    }
}
