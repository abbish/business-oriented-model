package com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.evidence;

import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class PaymentConfirmationEvidence implements Evidence<String> {
    private String evidenceName;
    private String evidenceId;
    private Instant createdAt;
    private String content;
}
