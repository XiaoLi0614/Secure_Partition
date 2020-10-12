package jsrc.matlab.typeinference.type;


/*
 User: lesani, Date: Nov 2, 2009, Time: 12:10:53 PM
*/


public interface Type extends lesani.compiler.typing.Type {
    public abstract boolean contains(TypeVar typeVar);
}