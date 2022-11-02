package com.ll.exam.final__2022_10_08.app.rebate.service;

import com.ll.exam.final__2022_10_08.app.order.entity.OrderItem;
import com.ll.exam.final__2022_10_08.app.order.repository.OrderItemRepository;
import com.ll.exam.final__2022_10_08.app.order.service.OrderService;
import com.ll.exam.final__2022_10_08.app.rebate.entity.RebateOrderItem;
import com.ll.exam.final__2022_10_08.app.rebate.repository.RebateOrderItemRepository;
import com.ll.exam.final__2022_10_08.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RebateService {

    private final OrderService orderService;
    private final RebateOrderItemRepository rebateOrderItemRepository;

    public void makeData(String yearMonth) {
        int monthEndDay = Ut.date.getEndDayOf(yearMonth);

        // 월초
        String fromDateStr = yearMonth + "-01 00:00:00.000000";
        // 월말
        String toDateStr = yearMonth + "-%02d 23:59:59.999999".formatted(monthEndDay);
        LocalDateTime fromDate = Ut.date.parse(fromDateStr);
        LocalDateTime toDate = Ut.date.parse(toDateStr);

        // 해당 범위의 모든 주문 품목 조회
        List<OrderItem> orderItems = orderService.findAllByPayDateBetween(fromDate, toDate);

        // OrderItem -> RebateOrderItem 변환
        List<RebateOrderItem> rebateOrderItems = orderItems.stream()
                .map(this::orderItemToRebateOrderItem)
                .collect(Collectors.toList());

        // 정산 데이터 저장
        rebateOrderItems.forEach(this::makeRebateOrderItem);
    }

    public void makeRebateOrderItem(RebateOrderItem item) {
        RebateOrderItem oldRebateOrderItem = rebateOrderItemRepository.findByOrderItemId(item.getOrderItem().getId()).orElse(null);

        if (oldRebateOrderItem != null) {
            rebateOrderItemRepository.delete(oldRebateOrderItem);
        }

        rebateOrderItemRepository.save(item);
    }

    public RebateOrderItem orderItemToRebateOrderItem(OrderItem orderItem) {
        return new RebateOrderItem(orderItem);
    }
}
