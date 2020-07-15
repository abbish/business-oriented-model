package com.abbish.businessorientedmodel.sample.salesorder.contract.fulfillment.payment.request;

import com.abbish.businessorientedmodel.oo.evidence.Evidence;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PaymentRequestEvidence implements Evidence<String> {
    private String evidenceName;
    private String evidenceId;
    private Instant createdAt;
    private String content;
}
