package com.abbish.thoughtworks.businessorientedmodel.oo.fulfillment;

import com.abbish.thoughtworks.businessorientedmodel.oo.contract.Contract;
import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;

@FunctionalInterface
public interface RequestConfirmationPlayer<C extends Contract, RE extends Evidence, CE extends Evidence, CC> {
    CE confirm(C contract, RE requestEvidence, CC confirmationCommand);
}
