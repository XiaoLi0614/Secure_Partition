#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__14 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__14> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 540 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__14* storage = x10aux::alloc<DoubleMatrix__closure__14>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__14>(storage));
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__14> this_ = new (storage) DoubleMatrix__closure__14(that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__14(x10aux::ref<DoubleMatrix> saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:539-540";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__13 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__13> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 334 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 336 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int i = ((x10_int) ((li) % (saved_this->FMGL(n))));
        
        //#line 337 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int j = ((x10_int) ((li) / (saved_this->FMGL(n))));
        
        //#line 338 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 i,
                 j);
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__13* storage = x10aux::alloc<DoubleMatrix__closure__13>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__13>(storage));
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__13> this_ = new (storage) DoubleMatrix__closure__13(that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__13(x10aux::ref<DoubleMatrix> saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:333-339";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__12 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__12> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 319 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 320 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return list->get(i);
        
    }
    
    // captured environment
    x10aux::ref<x10::util::ArrayList<x10_double> > list;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->list);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__12* storage = x10aux::alloc<DoubleMatrix__closure__12>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__12>(storage));
        x10aux::ref<x10::util::ArrayList<x10_double> > that_list = buf.read<x10aux::ref<x10::util::ArrayList<x10_double> > >();
        x10aux::ref<DoubleMatrix__closure__12> this_ = new (storage) DoubleMatrix__closure__12(that_list);
        return this_;
    }
    
    DoubleMatrix__closure__12(x10aux::ref<x10::util::ArrayList<x10_double> > list) : list(list) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:318-321";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__11 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__11> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 274 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)0)),
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)1)));
        
        //#line 278 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int rowCount = saved_this->FMGL(n);
        
        //#line 279 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int columnCount = saved_this->FMGL(m);
        
        //#line 280 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (((x10_int) ((rowCount) * (columnCount)))))) ||
            (((li) < (((x10_int)1))))) {
            
            //#line 281 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 283 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int i = ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) % (saved_this->
                                                                           FMGL(n))));
        
        //#line 284 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int j = ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) / (saved_this->
                                                                           FMGL(n))));
        
        //#line 285 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 i,
                 j);
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__11* storage = x10aux::alloc<DoubleMatrix__closure__11>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__11>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__11> this_ = new (storage) DoubleMatrix__closure__11(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__11(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:273-286";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__10 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__10> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 253 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)0)),
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)1)));
        
        //#line 257 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int rowCount = saved_this->FMGL(n);
        
        //#line 258 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (rowCount))) || (((li) < (((x10_int)1))))) {
            
            //#line 259 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 260 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((li) - (((x10_int)1)))),
                 ((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__10* storage = x10aux::alloc<DoubleMatrix__closure__10>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__10>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__10> this_ = new (storage) DoubleMatrix__closure__10(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__10(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:252-261";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__9 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__9> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 231 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)0)),
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)1)));
        
        //#line 235 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int columnCount = saved_this->FMGL(m);
        
        //#line 236 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (columnCount))) || (((li) < (((x10_int)1)))))
        {
            
            //#line 237 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(
              x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 238 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int)0),
                 ((x10_int) ((li) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__9* storage = x10aux::alloc<DoubleMatrix__closure__9>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__9>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__9> this_ = new (storage) DoubleMatrix__closure__9(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__9(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:230-239";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__8 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__8> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 209 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)0)),
                       ((x10_int)0));
        
        //#line 213 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int rowCount = saved_this->FMGL(n);
        
        //#line 214 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (rowCount))) || (((li) < (((x10_int)1))))) {
            
            //#line 215 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 216 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((li) - (((x10_int)1)))),
                 ((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__8* storage = x10aux::alloc<DoubleMatrix__closure__8>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__8>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__8> this_ = new (storage) DoubleMatrix__closure__8(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__8(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:208-217";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__7 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__7> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 188 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       ((x10_int)0),
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)0)));
        
        //#line 192 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int rowCount = saved_this->FMGL(n);
        
        //#line 193 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (rowCount))) || (((li) < (((x10_int)1))))) {
            
            //#line 194 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 195 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((li) - (((x10_int)1)))),
                 ((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__7* storage = x10aux::alloc<DoubleMatrix__closure__7>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__7>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__7> this_ = new (storage) DoubleMatrix__closure__7(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__7(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:187-196";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__6 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__6> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 167 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)1)),
                       ((x10_int)0));
        
        //#line 171 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int columnCount = saved_this->FMGL(m);
        
        //#line 172 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (columnCount))) || (((li) < (((x10_int)1)))))
        {
            
            //#line 173 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(
              x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 174 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int)0),
                 ((x10_int) ((li) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__6* storage = x10aux::alloc<DoubleMatrix__closure__6>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__6>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__6> this_ = new (storage) DoubleMatrix__closure__6(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__6(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:166-175";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__5 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__5> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 146 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int li = x10aux::nullCheck(x10aux::nullCheck(index)->FMGL(array))->x10::array::Array<x10_int>::apply(
                       ((x10_int)0),
                       x10aux::nullCheck(p)->x10::array::Point::apply(
                         ((x10_int)1)));
        
        //#line 150 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10_int columnCount = saved_this->FMGL(m);
        
        //#line 151 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((((li) > (columnCount))) || (((li) < (((x10_int)1)))))
        {
            
            //#line 152 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            DoubleMatrix::error(
              x10aux::string_utils::lit("At linear indexing: Out of bounds linear index."));
        }
        
        //#line 153 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int)0),
                 ((x10_int) ((li) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> index;
    x10aux::ref<DoubleMatrix> saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->index);
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__5* storage = x10aux::alloc<DoubleMatrix__closure__5>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__5>(storage));
        x10aux::ref<IntMatrix> that_index = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix__closure__5> this_ = new (storage) DoubleMatrix__closure__5(that_index, that_saved_this);
        return this_;
    }
    
    DoubleMatrix__closure__5(x10aux::ref<IntMatrix> index, x10aux::ref<DoubleMatrix> saved_this) : index(index), saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:145-154";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__4 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__4> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 109 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(i)->
                                                  FMGL(array))->x10::array::Array<x10_int>::apply(
                                ((x10_int)0),
                                x10aux::nullCheck(p)->x10::array::Point::apply(
                                  ((x10_int)0)))) - (((x10_int)1)))),
                 ((x10_int) ((j) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> saved_this;
    x10aux::ref<IntMatrix> i;
    x10_int j;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->i);
        buf.write(this->j);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__4* storage = x10aux::alloc<DoubleMatrix__closure__4>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__4>(storage));
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_i = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_j = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix__closure__4> this_ = new (storage) DoubleMatrix__closure__4(that_saved_this, that_i, that_j);
        return this_;
    }
    
    DoubleMatrix__closure__4(x10aux::ref<DoubleMatrix> saved_this, x10aux::ref<IntMatrix> i, x10_int j) : saved_this(saved_this), i(i), j(j) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:108-112";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__3 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__3> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 91 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((i) - (((x10_int)1)))),
                 ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(j)->
                                                  FMGL(array))->x10::array::Array<x10_int>::apply(
                                ((x10_int)0),
                                x10aux::nullCheck(p)->x10::array::Point::apply(
                                  ((x10_int)1)))) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> saved_this;
    x10_int i;
    x10aux::ref<IntMatrix> j;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->i);
        buf.write(this->j);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__3* storage = x10aux::alloc<DoubleMatrix__closure__3>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__3>(storage));
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_i = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_j = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix__closure__3> this_ = new (storage) DoubleMatrix__closure__3(that_saved_this, that_i, that_j);
        return this_;
    }
    
    DoubleMatrix__closure__3(x10aux::ref<DoubleMatrix> saved_this, x10_int i, x10aux::ref<IntMatrix> j) : saved_this(saved_this), i(i), j(j) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:90-94";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__2 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__2> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 73 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(saved_this->FMGL(array))->x10::array::Array<x10_double>::apply(
                 ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(i)->
                                                  FMGL(array))->x10::array::Array<x10_int>::apply(
                                ((x10_int)0),
                                x10aux::nullCheck(p)->x10::array::Point::apply(
                                  ((x10_int)0)))) - (((x10_int)1)))),
                 ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(j)->
                                                  FMGL(array))->x10::array::Array<x10_int>::apply(
                                ((x10_int)0),
                                x10aux::nullCheck(p)->x10::array::Point::apply(
                                  ((x10_int)1)))) - (((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> saved_this;
    x10aux::ref<IntMatrix> i;
    x10aux::ref<IntMatrix> j;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->i);
        buf.write(this->j);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__2* storage = x10aux::alloc<DoubleMatrix__closure__2>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__2>(storage));
        x10aux::ref<DoubleMatrix> that_saved_this = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_i = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_j = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix__closure__2> this_ = new (storage) DoubleMatrix__closure__2(that_saved_this, that_i, that_j);
        return this_;
    }
    
    DoubleMatrix__closure__2(x10aux::ref<DoubleMatrix> saved_this, x10aux::ref<IntMatrix> i, x10aux::ref<IntMatrix> j) : saved_this(saved_this), i(i), j(j) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:72-76";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__1 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__1> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::array::Array<x10_double> > array1 = (__extension__ ({
            x10aux::ref<x10::array::Array<x10_double> > __desugarer__var__3__ =
              x10aux::nullCheck(arrays)->x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > >::apply(
                x10aux::nullCheck(p)->x10::array::Point::apply(
                  ((x10_int)0)));
            
            //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::array::Array<x10_double> > __var41__;
            
            //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Labeled_c
            goto __ret139; __ret139: 
            //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                if ((!x10aux::struct_equals(__desugarer__var__3__,
                                            x10aux::null)) &&
                    !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__3__)->
                                               FMGL(region)->
                                               FMGL(rank),
                                             ((x10_int)1)))))
                {
                    
                    //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.Array[x10.lang.Double]{self.region.rank==1}"))));
                }
                
                //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                __var41__ =
                  __desugarer__var__3__;
                
                //#line 42 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Branch_c
                goto __ret139_end_;
            }
            goto __ret139_next_; __ret139_next_: ;
            }
            while (false);
            goto __ret139_end_; __ret139_end_: ;
            __var41__;
        }))
        ;
        
        //#line 43 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(array1)->x10::array::Array<x10_double>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1)));
        
    }
    
    // captured environment
    x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->arrays);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__1* storage = x10aux::alloc<DoubleMatrix__closure__1>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__1>(storage));
        x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > that_arrays = buf.read<x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > >();
        x10aux::ref<DoubleMatrix__closure__1> this_ = new (storage) DoubleMatrix__closure__1(that_arrays);
        return this_;
    }
    
    DoubleMatrix__closure__1(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays) : arrays(arrays) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:41-44";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class DoubleMatrix__closure__0 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__0> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 22 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(array)->x10::array::Array<x10_double>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1)));
        
    }
    
    // captured environment
    x10aux::ref<x10::array::Array<x10_double> > array;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->array);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        DoubleMatrix__closure__0* storage = x10aux::alloc<DoubleMatrix__closure__0>();
        buf.record_reference(x10aux::ref<DoubleMatrix__closure__0>(storage));
        x10aux::ref<x10::array::Array<x10_double> > that_array = buf.read<x10aux::ref<x10::array::Array<x10_double> > >();
        x10aux::ref<DoubleMatrix__closure__0> this_ = new (storage) DoubleMatrix__closure__0(that_array);
        return this_;
    }
    
    DoubleMatrix__closure__0(x10aux::ref<x10::array::Array<x10_double> > array) : array(array) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10:21-22";
    }

};

