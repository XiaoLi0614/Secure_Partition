package lesani.compiler.texttree.seq;

public class TextFact {
    private IntHolder intHolder;
    private VisitDispatcher visitDispatcher;

    public TextFact(/*VisitDispatcher visitDispatcher*/) {
        intHolder = new IntHolder(0);
        this.visitDispatcher = visitDispatcher;
    }

    public TextSeq newText() {
        return new TextSeq(intHolder/*, visitDispatcher*/);
    }
}
