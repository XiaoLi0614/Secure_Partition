package lesani.compiler.typing;

import lesani.collection.xtolook.Either;
import lesani.collection.func.Fun;
import lesani.compiler.typing.substitution.Substitution;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TypeScheme {
    public Set<TypeVar> typeVars;
    public Type type;

    public TypeScheme(Set<TypeVar> typeVars, Type type) {
        this.typeVars = typeVars;
        this.type = type;
    }

    public TypeScheme(Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.type = type;
    }

    public TypeScheme(Type type, TypeVar... typeVars) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.addAll(Arrays.asList(typeVars));
        this.type = type;
    }

    public TypeScheme(TypeVar typeVar1, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.type = type;
    }

    public TypeScheme(TypeVar typeVar1, TypeVar typeVar2, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.typeVars.add(typeVar2);
        this.type = type;
    }

    public TypeScheme(TypeVar typeVar1, TypeVar typeVar2, TypeVar typeVar3, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.typeVars.add(typeVar2);
        this.typeVars.add(typeVar3);
        this.type = type;
    }

    public TypeScheme(TypeVar typeVar1, TypeVar typeVar2, TypeVar typeVar3, TypeVar typeVar4, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.typeVars.add(typeVar2);
        this.typeVars.add(typeVar3);
        this.typeVars.add(typeVar4);
        this.type = type;
    }

    public TypeScheme(TypeVar typeVar1, TypeVar typeVar2, TypeVar typeVar3, TypeVar typeVar4, TypeVar typeVar5, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.typeVars.add(typeVar2);
        this.typeVars.add(typeVar3);
        this.typeVars.add(typeVar4);
        this.typeVars.add(typeVar5);
        this.type = type;
    }
    public TypeScheme(TypeVar typeVar1, TypeVar typeVar2, TypeVar typeVar3, TypeVar typeVar4, TypeVar typeVar5, TypeVar typeVar6, Type type) {
        this.typeVars = new HashSet<TypeVar>();
        this.typeVars.add(typeVar1);
        this.typeVars.add(typeVar2);
        this.typeVars.add(typeVar3);
        this.typeVars.add(typeVar4);
        this.typeVars.add(typeVar5);
        this.typeVars.add(typeVar6);
        this.type = type;
    }

    public Type instantiate() {
        Substitution substitution = new Substitution();
        for (TypeVar typeVar : typeVars) {
            TypeVar newTypeVar = typeVar.iFresh();
//            System.out.println("typeVar = " + typeVar);
//            System.out.println("newTypeVar = " + newTypeVar);
            substitution = substitution.add(typeVar, newTypeVar);
        }
//        System.out.println("substitution = " + substitution);
        final Either<Type,Integer> applied = substitution.apply(type);
//        System.out.println("applied = " + applied);
        return applied.apply(
                new Fun<Type, Type>() {
                    public Type apply(Type input) {
                        return input;
                    }
                },
                new Fun<Integer, Type>() {
                    public Type apply(Integer input) {
                        throw new RuntimeException();
                    }
                }
        );
    }
}


//        Substitution substitution;
//        return substitution.apply(type);
