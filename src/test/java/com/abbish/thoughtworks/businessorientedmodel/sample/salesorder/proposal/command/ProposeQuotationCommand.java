package com.abbish.thoughtworks.businessorientedmodel.sample.salesorder.proposal.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProposeQuotationCommand {
    private String seller;
}
