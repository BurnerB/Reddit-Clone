package spring.reddit.clone.Model;

//To distinguish whether an app is an upvote or downvote
public enum VoteType {
    UPVOTE(1),DOWNVOTE(-1),
    ;

    VoteType(int direction){

    }
}
