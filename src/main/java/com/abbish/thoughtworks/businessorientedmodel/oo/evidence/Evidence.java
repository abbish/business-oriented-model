package com.abbish.thoughtworks.businessorientedmodel.oo.evidence;

import com.domainmodeling.dci.adk.core.data.DCIData;

import java.time.Instant;

public interface Evidence<D> extends DCIData {
    String getEvidenceId();

    String getEvidenceName();

    Instant getCreatedAt();

    D getContent();
}
