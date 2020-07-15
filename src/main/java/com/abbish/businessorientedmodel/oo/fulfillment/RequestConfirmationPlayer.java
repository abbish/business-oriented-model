package com.abbish.businessorientedmodel.oo.fulfillment;

import com.abbish.businessorientedmodel.oo.contract.Contract;
import com.abbish.businessorientedmodel.oo.evidence.Evidence;

@FunctionalInterface
public interface RequestConfirmationPlayer<C extends Contract, RE extends Evidence, CE extends Evidence, CC> {
    CE confirm(C contract, RE requestEvidence, CC confirmationCommand);
}
