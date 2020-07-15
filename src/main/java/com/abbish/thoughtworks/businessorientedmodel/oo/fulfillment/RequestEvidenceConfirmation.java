package com.abbish.thoughtworks.businessorientedmodel.oo.fulfillment;


import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;

public interface RequestEvidenceConfirmation<CE extends Evidence, CC> {
     CE confirm(CC command);
}
