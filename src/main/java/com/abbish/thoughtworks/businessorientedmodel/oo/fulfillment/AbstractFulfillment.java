package com.abbish.thoughtworks.businessorientedmodel.oo.fulfillment;

import com.abbish.thoughtworks.businessorientedmodel.oo.contract.Contract;
import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;
import com.domainmodeling.dci.adk.core.role.AbstractDCIRole;

public abstract class AbstractFulfillment<C extends Contract, RE extends Evidence, RC> extends AbstractDCIRole<C> implements Fulfillment<C, RE, RC> {
    public AbstractFulfillment(C data) {
        super(data);
    }

    final public <RCP extends RequestConfirmationPlayer, CE extends Evidence, CC> RequestConfirmationRole<RCP, CE, CC> requestConfirmation(RE requestEvidence) {
        return player -> (RequestEvidenceConfirmation<CE, CC>) command -> (CE) player.confirm(data(), requestEvidence, command);
    }
}
