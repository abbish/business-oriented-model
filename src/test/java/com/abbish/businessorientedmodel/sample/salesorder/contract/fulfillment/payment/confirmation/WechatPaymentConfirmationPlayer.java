package com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.confirmation;


import com.abbish.businessorientedmodel.oo.fulfillment.RequestConfirmationPlayer;
import com.abbish.businessorientedmodel.sample.salesorder.contract.Order;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.command.WechatPaymentConfirmationCommand;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.PaymentEvidenceType;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.WechatPaymentConfirmationEvidence;
import com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestEvidence;

import java.time.Instant;
import java.util.UUID;

public class WechatPaymentConfirmationPlayer implements RequestConfirmationPlayer<Order, PaymentRequestEvidence, WechatPaymentConfirmationEvidence, WechatPaymentConfirmationCommand> {
    @Override
    public WechatPaymentConfirmationEvidence confirm(Order contract, PaymentRequestEvidence requestEvidence, WechatPaymentConfirmationCommand confirmationCommand) {
        return WechatPaymentConfirmationEvidence.builder()
                .evidenceName(PaymentEvidenceType.CONFIRMATION_EVIDENCE.toString())
                .content(String.format("PRE:`%s` confirmed by wechat", requestEvidence.getContent()))
                .createdAt(Instant.now())
                .evidenceId(UUID.randomUUID().toString())
                .build();
    }
}
