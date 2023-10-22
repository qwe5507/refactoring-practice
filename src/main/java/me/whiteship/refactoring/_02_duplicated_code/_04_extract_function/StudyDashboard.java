package me.whiteship.refactoring._02_duplicated_code._04_extract_function;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StudyDashboard {

    private void printParticipants(int eventId) throws IOException {
        // Get github issue to check homework
        GHIssue issue = getGhIssue(eventId);

        // Get participants
        Set<String> participants = getUsernames(issue);

        // Print participants
        print(participants);
    }

    private void printReviewers() throws IOException {
        // Get github issue to check reviews
        GHIssue issue = getGhIssue(30);

        // Get reviewers
        Set<String> reviewers = getUsernames(issue);

        // Print reviewers
        print(reviewers);
    }

    private static void print(final Set<String> participants) {
        participants.forEach(System.out::println);
    }

    private static Set<String> getUsernames(final GHIssue issue) throws IOException {
        Set<String> usernames = new HashSet<>();
        issue.getComments().forEach(c -> usernames.add(c.getUserName()));
        return usernames;
    }

    private static GHIssue getGhIssue(final int eventId) throws IOException {
        GitHub gitHub = GitHub.connect();
        GHRepository repository = gitHub.getRepository("whiteship/live-study");
        GHIssue issue = repository.getIssue(eventId);
        return issue;
    }

    public static void main(String[] args) throws IOException {
        StudyDashboard studyDashboard = new StudyDashboard();
        studyDashboard.printReviewers();
        studyDashboard.printParticipants(15);
    }

}
