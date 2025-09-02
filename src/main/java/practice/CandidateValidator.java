package practice;

import java.util.Arrays;
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
        return Arrays.stream(candidate.getPeriodsInUkr().split(","))
                .mapToInt(period -> {
                    String[] years = period.split("-");
                    int start = Integer.parseInt(years[0]);
                    int end = Integer.parseInt(years[1]);
                    return end - start + 1;
                })
                .sum() >= REQUIRED_PERIOD_LIVING_IN_UKRAINE;
    }

}
