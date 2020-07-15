package com.abbish.thoughtworks.businessorientedmodel.oo.rfp;

import com.abbish.thoughtworks.businessorientedmodel.oo.proposal.Proposal;
import com.domainmodeling.dci.adk.core.data.DCIData;

public interface RequestForProposal<PC, P extends Proposal> extends DCIData {
    P propose(PC proposeCommand);
}
