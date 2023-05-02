package ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync;

import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;

public class QPOperationResult {

    public enum QPOperationStatus {
        NO_SUCH_QUALITY_PROFILE,
        QP_WITH_GIVEN_NAME_ALREADY_EXISTS,
        SUCCESS
    }

    private final QPOperationStatus qpOperationStatus;
    private final QualityProfile qualityProfile;

    public QPOperationResult(QPOperationStatus qpOperationStatus, QualityProfile qualityProfile) {
        this.qpOperationStatus = qpOperationStatus;
        this.qualityProfile = qualityProfile;
    }

    public QPOperationStatus getStatus() {
        return qpOperationStatus;
    }

    public QualityProfile getQualityProfile() {
        return qualityProfile;
    }
}
