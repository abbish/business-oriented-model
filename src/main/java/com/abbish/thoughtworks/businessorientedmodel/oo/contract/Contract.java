package com.abbish.thoughtworks.businessorientedmodel.oo.contract;

import com.abbish.thoughtworks.businessorientedmodel.oo.evidence.Evidence;
import com.abbish.thoughtworks.businessorientedmodel.oo.fulfillment.Fulfillment;
import com.domainmodeling.dci.adk.core.data.DCIData;
import com.domainmodeling.dci.adk.core.exception.DCIRoleInstanceBuildException;

import java.util.Map;
import java.util.Optional;

public interface Contract extends DCIData {

    default public <R extends Fulfillment> R fulfill(Class<R> fulfillmentClass) throws DCIRoleInstanceBuildException {
        return play(fulfillmentClass);
    }

    Map<String, Evidence> getEvidences();

    void putEvidence(String name, Evidence evidence);

    void removeEvidence(String name);

    Optional<Evidence> getEvidence(String name);

    boolean hasEvidence(String name);
}
