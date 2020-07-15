package com.abbish.businessorientedmodel.oo.proposal;

import com.abbish.businessorientedmodel.oo.contract.Contract;
import com.domainmodeling.dci.adk.core.data.DCIData;

public interface Proposal<SC, C extends Contract> extends DCIData {
    C sign(SC signCommand);
}
