package com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.confirmation;


import com.abbish.businessorientedmodel.oo.fulfillment.RequestConfirmationPlayer;
import com.abbish.businessorientedmodel.sample.salesorder.contract.Order;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.command.AlipayPaymentConfirmationCommand;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.AlipayPaymentConfirmationEvidence;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.PaymentEvidenceType;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestEvidence;

import java.time.Instant;
import java.util.UUID;

public class AlipayPaymentConfirmationPlayer implements RequestConfirmationPlayer<Order, PaymentRequestEvidence, AlipayPaymentConfirmationEvidence, AlipayPaymentConfirmationCommand> {

    @Override
    public AlipayPaymentConfirmationEvidence confirm(Order contract, PaymentRequestEvidence requestEvidence, AlipayPaymentConfirmationCommand confirmationCommand) {
        return AlipayPaymentConfirmationEvidence.builder()
                .evidenceName(PaymentEvidenceType.CONFIRMATION_EVIDENCE.toString())
                .content(String.format("PRE:`%s` confirmed by alipay", requestEvidence.getContent()))
                .createdAt(Instant.now())
                .evidenceId(UUID.randomUUID().toString())
                .build();
    }
}
