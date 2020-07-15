package com.abbish.businessorientedmodel.oo.fulfillment;


import com.abbish.businessorientedmodel.oo.evidence.Evidence;

public interface RequestEvidenceConfirmation<CE extends Evidence, CC> {
     CE confirm(CC command);
}
