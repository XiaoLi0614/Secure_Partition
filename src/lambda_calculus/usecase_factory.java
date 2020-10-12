package lambda_calculus;

import jsrc.x10.ast.tree.type.*;
import jsrc.x10.ast.visitor.CPSPrinter;

import java.util.ArrayList;

public class usecase_factory extends lambda_usecase{

    public static lambda_usecase initUseCase(String name, Type rType, ArrayList<Type> inputArgus)
    {
        lambda_usecase useCase = new lambda_usecase(name, rType);
        useCase.arguments = inputArgus;

        return useCase;
    }
}
