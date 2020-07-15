package com.abbish.thoughtworks.businessorientedmodel.sample.salesorder;

import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.Order;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.PaymentFulfillment;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.command.AlipayPaymentConfirmationCommand;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.command.WechatPaymentConfirmationCommand;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.confirmation.AlipayPaymentConfirmationPlayer;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.confirmation.WechatPaymentConfirmationPlayer;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.AlipayPaymentConfirmationEvidence;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.PaymentEvidenceType;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence.WechatPaymentConfirmationEvidence;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestCommand;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request.PaymentRequestEvidence;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.proposal.Quotation;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.proposal.RequestForQuotation;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.proposal.command.ProposeQuotationCommand;
import com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.proposal.command.SignQuotationCommand;
import com.domainmodeling.dci.adk.core.exception.DCIRoleInstanceBuildException;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class SalesOrderTest {
    @Test
    public void shouldCreatePaymentFulfillmentEvidences() throws DCIRoleInstanceBuildException {

        //rfp
        RequestForQuotation rfp = RequestForQuotation.builder()
                .buyer("abbish")
                .quotationRequestNo("RFP001")
                .createdAt(Instant.now())
                .items(Lists.newArrayList(RequestForQuotation.QuotationRequestItem.builder()
                        .product("iphone")
                        .qty(1)
                        .build()))
                .build();

        //proposal
        Quotation proposal = rfp.propose(ProposeQuotationCommand.builder()
                .seller("grace")
                .build());

        //contract
        Order order = proposal.sign(SignQuotationCommand.builder().build());

        //fulfill payment
        PaymentRequestEvidence requestEvidence = order
                .fulfill(PaymentFulfillment.class)
                .request(PaymentRequestCommand.builder().build());

        assertEquals("order#001 payment requested",
                requestEvidence.getContent()
        );

        //confirm payment with wechat
        WechatPaymentConfirmationEvidence wechatConfirmationEvidence = (WechatPaymentConfirmationEvidence) order.fulfill(PaymentFulfillment.class)
                .requestConfirmation(requestEvidence)
                .withConfirmationPlayer(new WechatPaymentConfirmationPlayer())
                .confirm(WechatPaymentConfirmationCommand.builder().build());

        assertEquals("PRE:`order#001 payment requested` confirmed by wechat",
                wechatConfirmationEvidence.getContent()
        );

        //confirm payment with alipay
        AlipayPaymentConfirmationEvidence alipayConfirmationEvidence = (AlipayPaymentConfirmationEvidence) order.fulfill(PaymentFulfillment.class)
                .requestConfirmation(requestEvidence)
                .withConfirmationPlayer(new AlipayPaymentConfirmationPlayer())
                .confirm(AlipayPaymentConfirmationCommand.builder().build());

        assertEquals("PRE:`order#001 payment requested` confirmed by alipay",
                alipayConfirmationEvidence.getContent()
        );

        //confirm payment with lambda
        AlipayPaymentConfirmationEvidence lambdaConfirmationEvidence = (AlipayPaymentConfirmationEvidence) order.fulfill(PaymentFulfillment.class)
                .requestConfirmation(requestEvidence)
                .withConfirmationPlayer((contract, requestEvidence1, confirmationCommand) -> AlipayPaymentConfirmationEvidence.builder()
                        .evidenceName(PaymentEvidenceType.CONFIRMATION_EVIDENCE.toString())
                        .content(String.format("PRE:`%s` confirmed by lambda", requestEvidence1.getContent()))
                        .createdAt(Instant.now())
                        .evidenceId(UUID.randomUUID().toString())
                        .build())
                .confirm(AlipayPaymentConfirmationCommand.builder().build());

        assertEquals("PRE:`order#001 payment requested` confirmed by lambda",
                lambdaConfirmationEvidence.getContent()
        );

        // merge sub context result to main context
        order.putEvidence(requestEvidence.getEvidenceName(), requestEvidence);
        order.putEvidence(wechatConfirmationEvidence.getEvidenceName(), wechatConfirmationEvidence);
        order.putEvidence(alipayConfirmationEvidence.getEvidenceName(), alipayConfirmationEvidence);
        order.putEvidence(lambdaConfirmationEvidence.getEvidenceName(), lambdaConfirmationEvidence);

        assertEquals(2, order.getEvidences().size());

    }
}