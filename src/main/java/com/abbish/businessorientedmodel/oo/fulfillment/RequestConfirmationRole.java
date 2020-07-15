package com.abbish.businessorientedmodel.oo.fulfillment;

import com.abbish.businessorientedmodel.oo.evidence.Evidence;

@FunctionalInterface
public interface RequestConfirmationRole<RCP extends RequestConfirmationPlayer, CE extends Evidence, CC> {
    public RequestEvidenceConfirmation<CE, CC> withConfirmationPlayer(RCP player);
}
