package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import org.springframework.stereotype.Component;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.QualityProfile;
import ru.nsu.fit.g20203.sdwm.midpointsonar.qualityprofile.Rule;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.QPRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.async.RuleRunResult;
import ru.nsu.fit.g20203.sdwm.midpointsonar.result.sync.QPOperationResult;

import java.util.ArrayList;
import java.util.List;

@Component
public class QPRunner {

    public QPRunResult runQP(QualityProfile qualityProfile) {
        if (null == qualityProfile) {
            return new QPRunResult(new ArrayList<>(),
                    new QPOperationResult(QPOperationResult.QPOperationStatus.NO_SUCH_QUALITY_PROFILE,
                            null));
        }
        final List<RuleRunResult> ruleRunResults = new ArrayList<>();
        for (Rule rule : qualityProfile.getAllRules()) {
            ruleRunResults.add(RuleRunner.getInstance().runRule(rule));
        }
        QPRunResult result = new QPRunResult(ruleRunResults,
                new QPOperationResult(QPOperationResult.QPOperationStatus.SUCCESS, null));
        return result;
    }
}
