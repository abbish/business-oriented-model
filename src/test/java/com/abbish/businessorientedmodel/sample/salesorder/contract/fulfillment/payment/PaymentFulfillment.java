package com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment;


import com.abbish.businessorientedmodel.oo.fulfillment.AbstractFulfillment;
import com.abbish.businessorientedmodel.sample.salesorder.contract.Order;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.PaymentEvidenceType;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestCommand;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestEvidence;

import java.time.Instant;
import java.util.UUID;

public class PaymentFulfillment extends AbstractFulfillment<Order, PaymentRequestEvidence, PaymentRequestCommand> {
    public PaymentFulfillment(Order data) {
        super(data);
    }

    @Override
    public PaymentRequestEvidence request(PaymentRequestCommand requestCommand) {
        // ......
        // do some payment request logic
        // ......

        return PaymentRequestEvidence.builder()
                .evidenceName(PaymentEvidenceType.REQUEST_EVIDENCE.toString())
                .content(String.format("order#%s payment requested", data().getOrderNo()))
                .createdAt(Instant.now())
                .evidenceId(UUID.randomUUID().toString())
                .build();

    }
}
