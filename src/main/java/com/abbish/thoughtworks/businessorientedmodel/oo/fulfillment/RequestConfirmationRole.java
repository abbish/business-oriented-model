package com.abbish.thoughtworks.businessorientedmodel.oo.fulfillment;

import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;

@FunctionalInterface
public interface RequestConfirmationRole<RCP extends RequestConfirmationPlayer, CE extends Evidence, CC> {
    public RequestEvidenceConfirmation<CE, CC> withConfirmationPlayer(RCP player);
}
