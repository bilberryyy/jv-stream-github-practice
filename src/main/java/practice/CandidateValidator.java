package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int REQUIRED_AGE = 35;
    private static final String REQUIRED_NATIONALITY = "Ukrainian";
    private static final int REQUIRED_PERIOD_LIVING_IN_UKRAINE = 10;

    @Override
    public boolean test(Candidate candidate) {
        return (candidate.getAge() >= REQUIRED_AGE)
                && (candidate.isAllowedToVote())
                && (REQUIRED_NATIONALITY.equals(candidate.getNationality()))
                && hasLivedInUkraineForAtLeast10Years(candidate);
    }

    public boolean hasLivedInUkraineForAtLeast10Years(Candidate candidate) {
        String[] period = candidate.getPeriodsInUkr().split("-");
        int start = Integer.parseInt(period[0]);
        int end = Integer.parseInt(period[1]);
        return (end - start) >= REQUIRED_PERIOD_LIVING_IN_UKRAINE;
    }

}
