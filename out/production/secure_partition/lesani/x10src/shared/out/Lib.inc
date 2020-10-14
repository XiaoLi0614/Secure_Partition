#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__337 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__337> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8768 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8769 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (xv)),((x10_double) (yv)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__337* storage = x10aux::alloc<Lib__closure__337>();
        buf.record_reference(x10aux::ref<Lib__closure__337>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__337> this_ = new (storage) Lib__closure__337(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__337(x10aux::ref<BooleanMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8767-8770";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__336 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__336> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8749 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8750 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (xv)),((x10_double) (yv)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> y;
    x10_int xv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->xv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__336* storage = x10aux::alloc<Lib__closure__336>();
        buf.record_reference(x10aux::ref<Lib__closure__336>(storage));
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__336> this_ = new (storage) Lib__closure__336(that_y, that_xv);
        return this_;
    }
    
    Lib__closure__336(x10aux::ref<BooleanMatrix> y, x10_int xv) : y(y), xv(xv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8748-8751";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__335 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__335> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8727 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8728 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8729 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (xv)),((x10_double) (yv)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__335* storage = x10aux::alloc<Lib__closure__335>();
        buf.record_reference(x10aux::ref<Lib__closure__335>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__335> this_ = new (storage) Lib__closure__335(that_x, that_y);
        return this_;
    }
    
    Lib__closure__335(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8726-8730";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__334 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__334> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8694 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),((x10_double) (yv)));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__334* storage = x10aux::alloc<Lib__closure__334>();
        buf.record_reference(x10aux::ref<Lib__closure__334>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__334> this_ = new (storage) Lib__closure__334(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__334(x10aux::ref<DoubleMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8693-8694";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__333 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__333> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8678 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(((x10_double) (xv)),x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                               FMGL(array))->x10::array::Array<x10_double>::apply(
                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                               ((x10_int)0)),
                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                               ((x10_int)1))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__333* storage = x10aux::alloc<Lib__closure__333>();
        buf.record_reference(x10aux::ref<Lib__closure__333>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__333> this_ = new (storage) Lib__closure__333(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__333(x10_int xv, x10aux::ref<DoubleMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8677-8678";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__332 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__332> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8656 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                             ((x10_int)0)),
                                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                             ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__332* storage = x10aux::alloc<Lib__closure__332>();
        buf.record_reference(x10aux::ref<Lib__closure__332>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__332> this_ = new (storage) Lib__closure__332(that_x, that_y);
        return this_;
    }
    
    Lib__closure__332(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8655-8659";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__331 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__331> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8636 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))),x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__331* storage = x10aux::alloc<Lib__closure__331>();
        buf.record_reference(x10aux::ref<Lib__closure__331>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__331> this_ = new (storage) Lib__closure__331(that_x, that_y);
        return this_;
    }
    
    Lib__closure__331(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8635-8639";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__330 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__330> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8601 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)0)),
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)1))))),((x10_double) (yv)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__330* storage = x10aux::alloc<Lib__closure__330>();
        buf.record_reference(x10aux::ref<Lib__closure__330>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__330> this_ = new (storage) Lib__closure__330(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__330(x10aux::ref<IntMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8600-8601";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__329 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__329> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8585 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (xv)),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                          ((x10_int)0)),
                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                          ((x10_int)1))))))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__329* storage = x10aux::alloc<Lib__closure__329>();
        buf.record_reference(x10aux::ref<Lib__closure__329>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__329> this_ = new (storage) Lib__closure__329(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__329(x10_int xv, x10aux::ref<IntMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8584-8585";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__328 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__328> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8563 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)0)),
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)1))))),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                          ((x10_int)0)),
                                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                          ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__328* storage = x10aux::alloc<Lib__closure__328>();
        buf.record_reference(x10aux::ref<Lib__closure__328>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__328> this_ = new (storage) Lib__closure__328(that_x, that_y);
        return this_;
    }
    
    Lib__closure__328(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8562-8566";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__327 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__327> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8543 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                                      FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)0)),
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                         FMGL(array))->x10::array::Array<x10_int>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1))))))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__327* storage = x10aux::alloc<Lib__closure__327>();
        buf.record_reference(x10aux::ref<Lib__closure__327>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__327> this_ = new (storage) Lib__closure__327(that_x, that_y);
        return this_;
    }
    
    Lib__closure__327(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8542-8546";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__326 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__326> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8493 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),y);
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__326* storage = x10aux::alloc<Lib__closure__326>();
        buf.record_reference(x10aux::ref<Lib__closure__326>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__326> this_ = new (storage) Lib__closure__326(that_x, that_y);
        return this_;
    }
    
    Lib__closure__326(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8492-8493";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__325 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__325> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8477 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x,x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__325* storage = x10aux::alloc<Lib__closure__325>();
        buf.record_reference(x10aux::ref<Lib__closure__325>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__325> this_ = new (storage) Lib__closure__325(that_x, that_y);
        return this_;
    }
    
    Lib__closure__325(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8476-8477";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__324 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__324> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8460 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)0)),
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)1))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__324* storage = x10aux::alloc<Lib__closure__324>();
        buf.record_reference(x10aux::ref<Lib__closure__324>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__324> this_ = new (storage) Lib__closure__324(that_x, that_y);
        return this_;
    }
    
    Lib__closure__324(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8459-8460";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__323 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__323> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8411 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),((x10_double) (y)));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__323* storage = x10aux::alloc<Lib__closure__323>();
        buf.record_reference(x10aux::ref<Lib__closure__323>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__323> this_ = new (storage) Lib__closure__323(that_x, that_y);
        return this_;
    }
    
    Lib__closure__323(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8410-8411";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__322 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__322> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8395 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x,((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__322* storage = x10aux::alloc<Lib__closure__322>();
        buf.record_reference(x10aux::ref<Lib__closure__322>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__322> this_ = new (storage) Lib__closure__322(that_x, that_y);
        return this_;
    }
    
    Lib__closure__322(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8394-8395";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__321 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__321> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8378 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                             FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                             ((x10_int)0)),
                                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                             ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__321* storage = x10aux::alloc<Lib__closure__321>();
        buf.record_reference(x10aux::ref<Lib__closure__321>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__321> this_ = new (storage) Lib__closure__321(that_x, that_y);
        return this_;
    }
    
    Lib__closure__321(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8377-8378";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__320 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__320> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8329 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1))))),y);
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__320* storage = x10aux::alloc<Lib__closure__320>();
        buf.record_reference(x10aux::ref<Lib__closure__320>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__320> this_ = new (storage) Lib__closure__320(that_x, that_y);
        return this_;
    }
    
    Lib__closure__320(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8328-8329";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__319 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__319> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8313 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(((x10_double) (x)),x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)0)),
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)1))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__319* storage = x10aux::alloc<Lib__closure__319>();
        buf.record_reference(x10aux::ref<Lib__closure__319>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__319> this_ = new (storage) Lib__closure__319(that_x, that_y);
        return this_;
    }
    
    Lib__closure__319(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8312-8313";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__318 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__318> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8296 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1))))),x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                               FMGL(array))->x10::array::Array<x10_double>::apply(
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)0)),
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)1))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__318* storage = x10aux::alloc<Lib__closure__318>();
        buf.record_reference(x10aux::ref<Lib__closure__318>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__318> this_ = new (storage) Lib__closure__318(that_x, that_y);
        return this_;
    }
    
    Lib__closure__318(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8295-8296";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__317 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__317> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8247 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)0)),
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)1))))),((x10_double) (y)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__317* storage = x10aux::alloc<Lib__closure__317>();
        buf.record_reference(x10aux::ref<Lib__closure__317>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__317> this_ = new (storage) Lib__closure__317(that_x, that_y);
        return this_;
    }
    
    Lib__closure__317(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8246-8247";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__316 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__316> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8231 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x)),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                         FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                         ((x10_int)0)),
                                                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                         ((x10_int)1))))))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__316* storage = x10aux::alloc<Lib__closure__316>();
        buf.record_reference(x10aux::ref<Lib__closure__316>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__316> this_ = new (storage) Lib__closure__316(that_x, that_y);
        return this_;
    }
    
    Lib__closure__316(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8230-8231";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__315 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__315> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8214 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) (x10aux::math_utils::pow(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)0)),
                                                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                      ((x10_int)1))))),((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                          ((x10_int)0)),
                                                                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                          ((x10_int)1))))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__315* storage = x10aux::alloc<Lib__closure__315>();
        buf.record_reference(x10aux::ref<Lib__closure__315>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__315> this_ = new (storage) Lib__closure__315(that_x, that_y);
        return this_;
    }
    
    Lib__closure__315(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8213-8214";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__314 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__314> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8179 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8180 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) / (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__314* storage = x10aux::alloc<Lib__closure__314>();
        buf.record_reference(x10aux::ref<Lib__closure__314>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__314> this_ = new (storage) Lib__closure__314(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__314(x10aux::ref<BooleanMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8178-8181";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__313 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__313> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8160 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8161 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) / (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> y;
    x10_int xv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->xv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__313* storage = x10aux::alloc<Lib__closure__313>();
        buf.record_reference(x10aux::ref<Lib__closure__313>(storage));
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__313> this_ = new (storage) Lib__closure__313(that_y, that_xv);
        return this_;
    }
    
    Lib__closure__313(x10aux::ref<BooleanMatrix> y, x10_int xv) : y(y), xv(xv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8159-8162";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__312 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__312> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8138 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8139 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8140 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) / (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__312* storage = x10aux::alloc<Lib__closure__312>();
        buf.record_reference(x10aux::ref<Lib__closure__312>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__312> this_ = new (storage) Lib__closure__312(that_x, that_y);
        return this_;
    }
    
    Lib__closure__312(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8137-8141";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__311 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__311> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8104 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8105 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) * (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__311* storage = x10aux::alloc<Lib__closure__311>();
        buf.record_reference(x10aux::ref<Lib__closure__311>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__311> this_ = new (storage) Lib__closure__311(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__311(x10aux::ref<BooleanMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8103-8106";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__310 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__310> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8085 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8086 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) * (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> y;
    x10_int xv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->xv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__310* storage = x10aux::alloc<Lib__closure__310>();
        buf.record_reference(x10aux::ref<Lib__closure__310>(storage));
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__310> this_ = new (storage) Lib__closure__310(that_y, that_xv);
        return this_;
    }
    
    Lib__closure__310(x10aux::ref<BooleanMatrix> y, x10_int xv) : y(y), xv(xv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8084-8087";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__309 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__309> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8063 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8064 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8065 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) * (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__309* storage = x10aux::alloc<Lib__closure__309>();
        buf.record_reference(x10aux::ref<Lib__closure__309>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__309> this_ = new (storage) Lib__closure__309(that_x, that_y);
        return this_;
    }
    
    Lib__closure__309(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8062-8066";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__308 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__308> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8029 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8030 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) - (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__308* storage = x10aux::alloc<Lib__closure__308>();
        buf.record_reference(x10aux::ref<Lib__closure__308>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__308> this_ = new (storage) Lib__closure__308(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__308(x10aux::ref<BooleanMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8028-8031";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__307 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__307> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 8010 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 8011 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) - (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> y;
    x10_int xv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->xv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__307* storage = x10aux::alloc<Lib__closure__307>();
        buf.record_reference(x10aux::ref<Lib__closure__307>(storage));
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__307> this_ = new (storage) Lib__closure__307(that_y, that_xv);
        return this_;
    }
    
    Lib__closure__307(x10aux::ref<BooleanMatrix> y, x10_int xv) : y(y), xv(xv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:8009-8012";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__306 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__306> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7988 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7989 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7990 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) - (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__306* storage = x10aux::alloc<Lib__closure__306>();
        buf.record_reference(x10aux::ref<Lib__closure__306>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__306> this_ = new (storage) Lib__closure__306(that_x, that_y);
        return this_;
    }
    
    Lib__closure__306(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7987-7991";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__305 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__305> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7954 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7955 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) + (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__305* storage = x10aux::alloc<Lib__closure__305>();
        buf.record_reference(x10aux::ref<Lib__closure__305>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__305> this_ = new (storage) Lib__closure__305(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__305(x10aux::ref<BooleanMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7953-7956";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__304 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__304> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7935 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7936 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) + (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> y;
    x10_int xv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->xv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__304* storage = x10aux::alloc<Lib__closure__304>();
        buf.record_reference(x10aux::ref<Lib__closure__304>(storage));
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__304> this_ = new (storage) Lib__closure__304(that_y, that_xv);
        return this_;
    }
    
    Lib__closure__304(x10aux::ref<BooleanMatrix> y, x10_int xv) : y(y), xv(xv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7934-7937";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__303 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__303> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7913 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int xv = (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7914 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int yv = (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)0)),
                        x10aux::nullCheck(p)->x10::array::Point::apply(
                          ((x10_int)1)))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0));
        
        //#line 7915 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) + (yv)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__303* storage = x10aux::alloc<Lib__closure__303>();
        buf.record_reference(x10aux::ref<Lib__closure__303>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__303> this_ = new (storage) Lib__closure__303(that_x, that_y);
        return this_;
    }
    
    Lib__closure__303(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7912-7916";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__302 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__302> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7881 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (((x10_double) (yv))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__302* storage = x10aux::alloc<Lib__closure__302>();
        buf.record_reference(x10aux::ref<Lib__closure__302>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__302> this_ = new (storage) Lib__closure__302(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__302(x10aux::ref<DoubleMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7880-7881";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__301 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__301> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7865 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (xv))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__301* storage = x10aux::alloc<Lib__closure__301>();
        buf.record_reference(x10aux::ref<Lib__closure__301>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__301> this_ = new (storage) Lib__closure__301(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__301(x10_int xv, x10aux::ref<DoubleMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7864-7865";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__300 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__300> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7845 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__300* storage = x10aux::alloc<Lib__closure__300>();
        buf.record_reference(x10aux::ref<Lib__closure__300>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__300> this_ = new (storage) Lib__closure__300(that_x, that_y);
        return this_;
    }
    
    Lib__closure__300(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7844-7846";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__299 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__299> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7827 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__299* storage = x10aux::alloc<Lib__closure__299>();
        buf.record_reference(x10aux::ref<Lib__closure__299>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__299> this_ = new (storage) Lib__closure__299(that_x, that_y);
        return this_;
    }
    
    Lib__closure__299(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7826-7828";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__298 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__298> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7793 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (((x10_double) (yv))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__298* storage = x10aux::alloc<Lib__closure__298>();
        buf.record_reference(x10aux::ref<Lib__closure__298>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__298> this_ = new (storage) Lib__closure__298(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__298(x10aux::ref<DoubleMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7792-7793";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__297 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__297> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7777 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (xv))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__297* storage = x10aux::alloc<Lib__closure__297>();
        buf.record_reference(x10aux::ref<Lib__closure__297>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__297> this_ = new (storage) Lib__closure__297(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__297(x10_int xv, x10aux::ref<DoubleMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7776-7777";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__296 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__296> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7757 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__296* storage = x10aux::alloc<Lib__closure__296>();
        buf.record_reference(x10aux::ref<Lib__closure__296>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__296> this_ = new (storage) Lib__closure__296(that_x, that_y);
        return this_;
    }
    
    Lib__closure__296(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7756-7758";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__295 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__295> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7739 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__295* storage = x10aux::alloc<Lib__closure__295>();
        buf.record_reference(x10aux::ref<Lib__closure__295>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__295> this_ = new (storage) Lib__closure__295(that_x, that_y);
        return this_;
    }
    
    Lib__closure__295(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7738-7740";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__294 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__294> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7705 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (((x10_double) (yv))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__294* storage = x10aux::alloc<Lib__closure__294>();
        buf.record_reference(x10aux::ref<Lib__closure__294>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__294> this_ = new (storage) Lib__closure__294(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__294(x10aux::ref<DoubleMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7704-7705";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__293 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__293> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7689 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (xv))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__293* storage = x10aux::alloc<Lib__closure__293>();
        buf.record_reference(x10aux::ref<Lib__closure__293>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__293> this_ = new (storage) Lib__closure__293(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__293(x10_int xv, x10aux::ref<DoubleMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7688-7689";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__292 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__292> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7669 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__292* storage = x10aux::alloc<Lib__closure__292>();
        buf.record_reference(x10aux::ref<Lib__closure__292>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__292> this_ = new (storage) Lib__closure__292(that_x, that_y);
        return this_;
    }
    
    Lib__closure__292(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7668-7670";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__291 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__291> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7651 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__291* storage = x10aux::alloc<Lib__closure__291>();
        buf.record_reference(x10aux::ref<Lib__closure__291>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__291> this_ = new (storage) Lib__closure__291(that_x, that_y);
        return this_;
    }
    
    Lib__closure__291(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7650-7652";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__290 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__290> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7617 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (((x10_double) (yv))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__290* storage = x10aux::alloc<Lib__closure__290>();
        buf.record_reference(x10aux::ref<Lib__closure__290>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__290> this_ = new (storage) Lib__closure__290(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__290(x10aux::ref<DoubleMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7616-7617";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__289 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__289> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7601 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (xv))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__289* storage = x10aux::alloc<Lib__closure__289>();
        buf.record_reference(x10aux::ref<Lib__closure__289>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__289> this_ = new (storage) Lib__closure__289(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__289(x10_int xv, x10aux::ref<DoubleMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7600-7601";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__288 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__288> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7581 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__288* storage = x10aux::alloc<Lib__closure__288>();
        buf.record_reference(x10aux::ref<Lib__closure__288>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__288> this_ = new (storage) Lib__closure__288(that_x, that_y);
        return this_;
    }
    
    Lib__closure__288(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7580-7582";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__287 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__287> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7563 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__287* storage = x10aux::alloc<Lib__closure__287>();
        buf.record_reference(x10aux::ref<Lib__closure__287>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__287> this_ = new (storage) Lib__closure__287(that_x, that_y);
        return this_;
    }
    
    Lib__closure__287(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7562-7564";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__286 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__286> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7529 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) / (yv)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__286* storage = x10aux::alloc<Lib__closure__286>();
        buf.record_reference(x10aux::ref<Lib__closure__286>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__286> this_ = new (storage) Lib__closure__286(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__286(x10aux::ref<IntMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7528-7529";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__285 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__285> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7513 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)0)),
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__285* storage = x10aux::alloc<Lib__closure__285>();
        buf.record_reference(x10aux::ref<Lib__closure__285>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__285> this_ = new (storage) Lib__closure__285(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__285(x10_int xv, x10aux::ref<IntMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7512-7513";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__284 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__284> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7493 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__284* storage = x10aux::alloc<Lib__closure__284>();
        buf.record_reference(x10aux::ref<Lib__closure__284>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__284> this_ = new (storage) Lib__closure__284(that_x, that_y);
        return this_;
    }
    
    Lib__closure__284(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7492-7494";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__283 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__283> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7475 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__283* storage = x10aux::alloc<Lib__closure__283>();
        buf.record_reference(x10aux::ref<Lib__closure__283>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__283> this_ = new (storage) Lib__closure__283(that_x, that_y);
        return this_;
    }
    
    Lib__closure__283(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7474-7476";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__282 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__282> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7441 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) * (yv)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__282* storage = x10aux::alloc<Lib__closure__282>();
        buf.record_reference(x10aux::ref<Lib__closure__282>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__282> this_ = new (storage) Lib__closure__282(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__282(x10aux::ref<IntMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7440-7441";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__281 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__281> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7425 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)0)),
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__281* storage = x10aux::alloc<Lib__closure__281>();
        buf.record_reference(x10aux::ref<Lib__closure__281>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__281> this_ = new (storage) Lib__closure__281(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__281(x10_int xv, x10aux::ref<IntMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7424-7425";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__280 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__280> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7405 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__280* storage = x10aux::alloc<Lib__closure__280>();
        buf.record_reference(x10aux::ref<Lib__closure__280>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__280> this_ = new (storage) Lib__closure__280(that_x, that_y);
        return this_;
    }
    
    Lib__closure__280(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7404-7406";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__279 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__279> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7387 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__279* storage = x10aux::alloc<Lib__closure__279>();
        buf.record_reference(x10aux::ref<Lib__closure__279>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__279> this_ = new (storage) Lib__closure__279(that_x, that_y);
        return this_;
    }
    
    Lib__closure__279(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7386-7388";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__278 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__278> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7353 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) - (yv)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__278* storage = x10aux::alloc<Lib__closure__278>();
        buf.record_reference(x10aux::ref<Lib__closure__278>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__278> this_ = new (storage) Lib__closure__278(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__278(x10aux::ref<IntMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7352-7353";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__277 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__277> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7337 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)0)),
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__277* storage = x10aux::alloc<Lib__closure__277>();
        buf.record_reference(x10aux::ref<Lib__closure__277>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__277> this_ = new (storage) Lib__closure__277(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__277(x10_int xv, x10aux::ref<IntMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7336-7337";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__276 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__276> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7317 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__276* storage = x10aux::alloc<Lib__closure__276>();
        buf.record_reference(x10aux::ref<Lib__closure__276>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__276> this_ = new (storage) Lib__closure__276(that_x, that_y);
        return this_;
    }
    
    Lib__closure__276(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7316-7318";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__275 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__275> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7299 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__275* storage = x10aux::alloc<Lib__closure__275>();
        buf.record_reference(x10aux::ref<Lib__closure__275>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__275> this_ = new (storage) Lib__closure__275(that_x, that_y);
        return this_;
    }
    
    Lib__closure__275(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7298-7300";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__274 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__274> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7265 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) + (yv)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int yv;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->yv);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__274* storage = x10aux::alloc<Lib__closure__274>();
        buf.record_reference(x10aux::ref<Lib__closure__274>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_yv = buf.read<x10_int>();
        x10aux::ref<Lib__closure__274> this_ = new (storage) Lib__closure__274(that_x, that_yv);
        return this_;
    }
    
    Lib__closure__274(x10aux::ref<IntMatrix> x, x10_int yv) : x(x), yv(yv) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7264-7265";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__273 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__273> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7249 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((xv) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)0)),
                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int xv;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->xv);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__273* storage = x10aux::alloc<Lib__closure__273>();
        buf.record_reference(x10aux::ref<Lib__closure__273>(storage));
        x10_int that_xv = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__273> this_ = new (storage) Lib__closure__273(that_xv, that_y);
        return this_;
    }
    
    Lib__closure__273(x10_int xv, x10aux::ref<IntMatrix> y) : xv(xv), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7248-7249";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__272 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__272> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7229 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__272* storage = x10aux::alloc<Lib__closure__272>();
        buf.record_reference(x10aux::ref<Lib__closure__272>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__272> this_ = new (storage) Lib__closure__272(that_x, that_y);
        return this_;
    }
    
    Lib__closure__272(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7228-7230";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__271 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__271> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7211 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__271* storage = x10aux::alloc<Lib__closure__271>();
        buf.record_reference(x10aux::ref<Lib__closure__271>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__271> this_ = new (storage) Lib__closure__271(that_x, that_y);
        return this_;
    }
    
    Lib__closure__271(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7210-7212";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__270 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__270> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7161 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__270* storage = x10aux::alloc<Lib__closure__270>();
        buf.record_reference(x10aux::ref<Lib__closure__270>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__270> this_ = new (storage) Lib__closure__270(that_x, that_y);
        return this_;
    }
    
    Lib__closure__270(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7160-7161";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__269 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__269> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7145 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) / (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__269* storage = x10aux::alloc<Lib__closure__269>();
        buf.record_reference(x10aux::ref<Lib__closure__269>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__269> this_ = new (storage) Lib__closure__269(that_x, that_y);
        return this_;
    }
    
    Lib__closure__269(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7144-7145";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__268 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__268> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7128 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__268* storage = x10aux::alloc<Lib__closure__268>();
        buf.record_reference(x10aux::ref<Lib__closure__268>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__268> this_ = new (storage) Lib__closure__268(that_x, that_y);
        return this_;
    }
    
    Lib__closure__268(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7127-7128";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__267 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__267> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7079 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__267* storage = x10aux::alloc<Lib__closure__267>();
        buf.record_reference(x10aux::ref<Lib__closure__267>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__267> this_ = new (storage) Lib__closure__267(that_x, that_y);
        return this_;
    }
    
    Lib__closure__267(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7078-7079";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__266 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__266> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7063 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) * (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__266* storage = x10aux::alloc<Lib__closure__266>();
        buf.record_reference(x10aux::ref<Lib__closure__266>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__266> this_ = new (storage) Lib__closure__266(that_x, that_y);
        return this_;
    }
    
    Lib__closure__266(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7062-7063";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__265 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__265> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 7046 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__265* storage = x10aux::alloc<Lib__closure__265>();
        buf.record_reference(x10aux::ref<Lib__closure__265>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__265> this_ = new (storage) Lib__closure__265(that_x, that_y);
        return this_;
    }
    
    Lib__closure__265(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:7045-7046";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__264 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__264> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6997 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__264* storage = x10aux::alloc<Lib__closure__264>();
        buf.record_reference(x10aux::ref<Lib__closure__264>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__264> this_ = new (storage) Lib__closure__264(that_x, that_y);
        return this_;
    }
    
    Lib__closure__264(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6996-6997";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__263 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__263> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6981 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) - (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__263* storage = x10aux::alloc<Lib__closure__263>();
        buf.record_reference(x10aux::ref<Lib__closure__263>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__263> this_ = new (storage) Lib__closure__263(that_x, that_y);
        return this_;
    }
    
    Lib__closure__263(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6980-6981";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__262 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__262> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6964 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__262* storage = x10aux::alloc<Lib__closure__262>();
        buf.record_reference(x10aux::ref<Lib__closure__262>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__262> this_ = new (storage) Lib__closure__262(that_x, that_y);
        return this_;
    }
    
    Lib__closure__262(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6963-6964";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__261 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__261> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6915 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__261* storage = x10aux::alloc<Lib__closure__261>();
        buf.record_reference(x10aux::ref<Lib__closure__261>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__261> this_ = new (storage) Lib__closure__261(that_x, that_y);
        return this_;
    }
    
    Lib__closure__261(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6914-6915";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__260 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__260> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6899 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) + (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__260* storage = x10aux::alloc<Lib__closure__260>();
        buf.record_reference(x10aux::ref<Lib__closure__260>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__260> this_ = new (storage) Lib__closure__260(that_x, that_y);
        return this_;
    }
    
    Lib__closure__260(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6898-6899";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__259 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__259> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6882 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__259* storage = x10aux::alloc<Lib__closure__259>();
        buf.record_reference(x10aux::ref<Lib__closure__259>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__259> this_ = new (storage) Lib__closure__259(that_x, that_y);
        return this_;
    }
    
    Lib__closure__259(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6881-6882";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__258 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__258> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6833 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__258* storage = x10aux::alloc<Lib__closure__258>();
        buf.record_reference(x10aux::ref<Lib__closure__258>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__258> this_ = new (storage) Lib__closure__258(that_x, that_y);
        return this_;
    }
    
    Lib__closure__258(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6832-6833";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__257 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__257> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6817 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) / (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__257* storage = x10aux::alloc<Lib__closure__257>();
        buf.record_reference(x10aux::ref<Lib__closure__257>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__257> this_ = new (storage) Lib__closure__257(that_x, that_y);
        return this_;
    }
    
    Lib__closure__257(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6816-6817";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__256 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__256> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6800 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) / (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__256* storage = x10aux::alloc<Lib__closure__256>();
        buf.record_reference(x10aux::ref<Lib__closure__256>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__256> this_ = new (storage) Lib__closure__256(that_x, that_y);
        return this_;
    }
    
    Lib__closure__256(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6799-6800";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__255 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__255> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6751 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__255* storage = x10aux::alloc<Lib__closure__255>();
        buf.record_reference(x10aux::ref<Lib__closure__255>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__255> this_ = new (storage) Lib__closure__255(that_x, that_y);
        return this_;
    }
    
    Lib__closure__255(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6750-6751";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__254 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__254> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6735 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) * (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__254* storage = x10aux::alloc<Lib__closure__254>();
        buf.record_reference(x10aux::ref<Lib__closure__254>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__254> this_ = new (storage) Lib__closure__254(that_x, that_y);
        return this_;
    }
    
    Lib__closure__254(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6734-6735";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__253 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__253> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6718 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) * (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__253* storage = x10aux::alloc<Lib__closure__253>();
        buf.record_reference(x10aux::ref<Lib__closure__253>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__253> this_ = new (storage) Lib__closure__253(that_x, that_y);
        return this_;
    }
    
    Lib__closure__253(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6717-6718";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__252 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__252> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6669 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__252* storage = x10aux::alloc<Lib__closure__252>();
        buf.record_reference(x10aux::ref<Lib__closure__252>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__252> this_ = new (storage) Lib__closure__252(that_x, that_y);
        return this_;
    }
    
    Lib__closure__252(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6668-6669";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__251 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__251> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6653 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) - (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__251* storage = x10aux::alloc<Lib__closure__251>();
        buf.record_reference(x10aux::ref<Lib__closure__251>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__251> this_ = new (storage) Lib__closure__251(that_x, that_y);
        return this_;
    }
    
    Lib__closure__251(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6652-6653";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__250 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__250> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6636 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) - (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__250* storage = x10aux::alloc<Lib__closure__250>();
        buf.record_reference(x10aux::ref<Lib__closure__250>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__250> this_ = new (storage) Lib__closure__250(that_x, that_y);
        return this_;
    }
    
    Lib__closure__250(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6635-6636";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__249 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__249> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6587 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__249* storage = x10aux::alloc<Lib__closure__249>();
        buf.record_reference(x10aux::ref<Lib__closure__249>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__249> this_ = new (storage) Lib__closure__249(that_x, that_y);
        return this_;
    }
    
    Lib__closure__249(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6586-6587";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__248 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__248> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6571 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) + (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__248* storage = x10aux::alloc<Lib__closure__248>();
        buf.record_reference(x10aux::ref<Lib__closure__248>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__248> this_ = new (storage) Lib__closure__248(that_x, that_y);
        return this_;
    }
    
    Lib__closure__248(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6570-6571";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__247 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__247> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6554 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) + (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__247* storage = x10aux::alloc<Lib__closure__247>();
        buf.record_reference(x10aux::ref<Lib__closure__247>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__247> this_ = new (storage) Lib__closure__247(that_x, that_y);
        return this_;
    }
    
    Lib__closure__247(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6553-6554";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__246 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__246> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6505 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) / (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__246* storage = x10aux::alloc<Lib__closure__246>();
        buf.record_reference(x10aux::ref<Lib__closure__246>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__246> this_ = new (storage) Lib__closure__246(that_x, that_y);
        return this_;
    }
    
    Lib__closure__246(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6504-6505";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__245 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__245> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6489 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__245* storage = x10aux::alloc<Lib__closure__245>();
        buf.record_reference(x10aux::ref<Lib__closure__245>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__245> this_ = new (storage) Lib__closure__245(that_x, that_y);
        return this_;
    }
    
    Lib__closure__245(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6488-6489";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__244 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__244> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__244* storage = x10aux::alloc<Lib__closure__244>();
        buf.record_reference(x10aux::ref<Lib__closure__244>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__244> this_ = new (storage) Lib__closure__244(that_x, that_y);
        return this_;
    }
    
    Lib__closure__244(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6471-6472";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__243 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__243> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6423 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) * (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__243* storage = x10aux::alloc<Lib__closure__243>();
        buf.record_reference(x10aux::ref<Lib__closure__243>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__243> this_ = new (storage) Lib__closure__243(that_x, that_y);
        return this_;
    }
    
    Lib__closure__243(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6422-6423";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__242 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__242> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6407 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__242* storage = x10aux::alloc<Lib__closure__242>();
        buf.record_reference(x10aux::ref<Lib__closure__242>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__242> this_ = new (storage) Lib__closure__242(that_x, that_y);
        return this_;
    }
    
    Lib__closure__242(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6406-6407";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__241 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__241> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6390 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__241* storage = x10aux::alloc<Lib__closure__241>();
        buf.record_reference(x10aux::ref<Lib__closure__241>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__241> this_ = new (storage) Lib__closure__241(that_x, that_y);
        return this_;
    }
    
    Lib__closure__241(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6389-6390";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__240 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__240> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6341 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) - (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__240* storage = x10aux::alloc<Lib__closure__240>();
        buf.record_reference(x10aux::ref<Lib__closure__240>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__240> this_ = new (storage) Lib__closure__240(that_x, that_y);
        return this_;
    }
    
    Lib__closure__240(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6340-6341";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__239 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__239> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6325 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__239* storage = x10aux::alloc<Lib__closure__239>();
        buf.record_reference(x10aux::ref<Lib__closure__239>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__239> this_ = new (storage) Lib__closure__239(that_x, that_y);
        return this_;
    }
    
    Lib__closure__239(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6324-6325";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__238 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__238> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6308 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__238* storage = x10aux::alloc<Lib__closure__238>();
        buf.record_reference(x10aux::ref<Lib__closure__238>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__238> this_ = new (storage) Lib__closure__238(that_x, that_y);
        return this_;
    }
    
    Lib__closure__238(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6307-6308";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__237 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__237> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6259 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) + (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__237* storage = x10aux::alloc<Lib__closure__237>();
        buf.record_reference(x10aux::ref<Lib__closure__237>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__237> this_ = new (storage) Lib__closure__237(that_x, that_y);
        return this_;
    }
    
    Lib__closure__237(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6258-6259";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__236 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__236> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6243 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__236* storage = x10aux::alloc<Lib__closure__236>();
        buf.record_reference(x10aux::ref<Lib__closure__236>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__236> this_ = new (storage) Lib__closure__236(that_x, that_y);
        return this_;
    }
    
    Lib__closure__236(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6242-6243";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__235 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__235> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6226 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__235* storage = x10aux::alloc<Lib__closure__235>();
        buf.record_reference(x10aux::ref<Lib__closure__235>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__235> this_ = new (storage) Lib__closure__235(that_x, that_y);
        return this_;
    }
    
    Lib__closure__235(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6225-6226";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__234 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__234> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6177 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) / (y)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__234* storage = x10aux::alloc<Lib__closure__234>();
        buf.record_reference(x10aux::ref<Lib__closure__234>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__234> this_ = new (storage) Lib__closure__234(that_x, that_y);
        return this_;
    }
    
    Lib__closure__234(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6176-6177";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__233 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__233> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6161 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)0)),
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__233* storage = x10aux::alloc<Lib__closure__233>();
        buf.record_reference(x10aux::ref<Lib__closure__233>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__233> this_ = new (storage) Lib__closure__233(that_x, that_y);
        return this_;
    }
    
    Lib__closure__233(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6160-6161";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__232 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__232> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6144 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) / (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__232* storage = x10aux::alloc<Lib__closure__232>();
        buf.record_reference(x10aux::ref<Lib__closure__232>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__232> this_ = new (storage) Lib__closure__232(that_x, that_y);
        return this_;
    }
    
    Lib__closure__232(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6143-6144";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__231 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__231> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6095 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) * (y)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__231* storage = x10aux::alloc<Lib__closure__231>();
        buf.record_reference(x10aux::ref<Lib__closure__231>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__231> this_ = new (storage) Lib__closure__231(that_x, that_y);
        return this_;
    }
    
    Lib__closure__231(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6094-6095";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__230 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__230> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6079 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)0)),
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__230* storage = x10aux::alloc<Lib__closure__230>();
        buf.record_reference(x10aux::ref<Lib__closure__230>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__230> this_ = new (storage) Lib__closure__230(that_x, that_y);
        return this_;
    }
    
    Lib__closure__230(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6078-6079";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__229 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__229> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6062 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) * (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__229* storage = x10aux::alloc<Lib__closure__229>();
        buf.record_reference(x10aux::ref<Lib__closure__229>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__229> this_ = new (storage) Lib__closure__229(that_x, that_y);
        return this_;
    }
    
    Lib__closure__229(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6061-6062";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__228 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__228> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 6013 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) - (y)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__228* storage = x10aux::alloc<Lib__closure__228>();
        buf.record_reference(x10aux::ref<Lib__closure__228>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__228> this_ = new (storage) Lib__closure__228(that_x, that_y);
        return this_;
    }
    
    Lib__closure__228(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:6012-6013";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__227 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__227> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5997 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)0)),
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__227* storage = x10aux::alloc<Lib__closure__227>();
        buf.record_reference(x10aux::ref<Lib__closure__227>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__227> this_ = new (storage) Lib__closure__227(that_x, that_y);
        return this_;
    }
    
    Lib__closure__227(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5996-5997";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__226 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__226> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5980 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) - (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__226* storage = x10aux::alloc<Lib__closure__226>();
        buf.record_reference(x10aux::ref<Lib__closure__226>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__226> this_ = new (storage) Lib__closure__226(that_x, that_y);
        return this_;
    }
    
    Lib__closure__226(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5979-5980";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__225 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__225> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5931 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) + (y)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__225* storage = x10aux::alloc<Lib__closure__225>();
        buf.record_reference(x10aux::ref<Lib__closure__225>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__225> this_ = new (storage) Lib__closure__225(that_x, that_y);
        return this_;
    }
    
    Lib__closure__225(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5930-5931";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__224 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__224> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5915 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)0)),
                                    x10aux::nullCheck(p)->x10::array::Point::apply(
                                      ((x10_int)1))))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__224* storage = x10aux::alloc<Lib__closure__224>();
        buf.record_reference(x10aux::ref<Lib__closure__224>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__224> this_ = new (storage) Lib__closure__224(that_x, that_y);
        return this_;
    }
    
    Lib__closure__224(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5914-5915";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__223 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__223> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5898 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))) + (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)0)),
                                                     x10aux::nullCheck(p)->x10::array::Point::apply(
                                                       ((x10_int)1))))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__223* storage = x10aux::alloc<Lib__closure__223>();
        buf.record_reference(x10aux::ref<Lib__closure__223>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__223> this_ = new (storage) Lib__closure__223(that_x, that_y);
        return this_;
    }
    
    Lib__closure__223(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5897-5898";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__222 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__222> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5864 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1))) || y;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__222* storage = x10aux::alloc<Lib__closure__222>();
        buf.record_reference(x10aux::ref<Lib__closure__222>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__222> this_ = new (storage) Lib__closure__222(that_x, that_y);
        return this_;
    }
    
    Lib__closure__222(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5863-5864";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__221 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__221> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5850 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x || x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                      x10aux::nullCheck(p)->x10::array::Point::apply(
                        ((x10_int)0)),
                      x10aux::nullCheck(p)->x10::array::Point::apply(
                        ((x10_int)1)));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__221* storage = x10aux::alloc<Lib__closure__221>();
        buf.record_reference(x10aux::ref<Lib__closure__221>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__221> this_ = new (storage) Lib__closure__221(that_x, that_y);
        return this_;
    }
    
    Lib__closure__221(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5849-5850";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__220 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__220> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5836 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1))) || x10aux::nullCheck(x10aux::nullCheck(y)->
                                                         FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                         ((x10_int)0)),
                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                         ((x10_int)1)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__220* storage = x10aux::alloc<Lib__closure__220>();
        buf.record_reference(x10aux::ref<Lib__closure__220>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__220> this_ = new (storage) Lib__closure__220(that_x, that_y);
        return this_;
    }
    
    Lib__closure__220(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5835-5836";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__219 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__219> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5807 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1))) && y;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__219* storage = x10aux::alloc<Lib__closure__219>();
        buf.record_reference(x10aux::ref<Lib__closure__219>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__219> this_ = new (storage) Lib__closure__219(that_x, that_y);
        return this_;
    }
    
    Lib__closure__219(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5806-5807";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__218 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__218> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5793 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x && x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                      x10aux::nullCheck(p)->x10::array::Point::apply(
                        ((x10_int)0)),
                      x10aux::nullCheck(p)->x10::array::Point::apply(
                        ((x10_int)1)));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__218* storage = x10aux::alloc<Lib__closure__218>();
        buf.record_reference(x10aux::ref<Lib__closure__218>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__218> this_ = new (storage) Lib__closure__218(that_x, that_y);
        return this_;
    }
    
    Lib__closure__218(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5792-5793";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__217 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__217> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5779 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1))) && x10aux::nullCheck(x10aux::nullCheck(y)->
                                                         FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                         ((x10_int)0)),
                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                         ((x10_int)1)));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__217* storage = x10aux::alloc<Lib__closure__217>();
        buf.record_reference(x10aux::ref<Lib__closure__217>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__217> this_ = new (storage) Lib__closure__217(that_x, that_y);
        return this_;
    }
    
    Lib__closure__217(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5778-5779";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__216 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__216> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5639 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1 = x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5640 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 5641 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 5642 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 5644 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 5645 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__216* storage = x10aux::alloc<Lib__closure__216>();
        buf.record_reference(x10aux::ref<Lib__closure__216>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__216> this_ = new (storage) Lib__closure__216(that_x, that_y);
        return this_;
    }
    
    Lib__closure__216(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5638-5646";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__215 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__215> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5613 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 5614 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 5615 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 5617 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 5618 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2 = x10aux::nullCheck(x10aux::nullCheck(y)->
                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5620 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__215* storage = x10aux::alloc<Lib__closure__215>();
        buf.record_reference(x10aux::ref<Lib__closure__215>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__215> this_ = new (storage) Lib__closure__215(that_x, that_y);
        return this_;
    }
    
    Lib__closure__215(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5612-5621";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__214 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__214> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5454 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1 = x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5455 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 5456 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 5457 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 5459 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 5460 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__214* storage = x10aux::alloc<Lib__closure__214>();
        buf.record_reference(x10aux::ref<Lib__closure__214>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__214> this_ = new (storage) Lib__closure__214(that_x, that_y);
        return this_;
    }
    
    Lib__closure__214(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5453-5461";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__213 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__213> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5428 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 5429 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 5430 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 5432 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 5433 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2 = x10aux::nullCheck(x10aux::nullCheck(y)->
                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5435 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__213* storage = x10aux::alloc<Lib__closure__213>();
        buf.record_reference(x10aux::ref<Lib__closure__213>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__213> this_ = new (storage) Lib__closure__213(that_x, that_y);
        return this_;
    }
    
    Lib__closure__213(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5427-5436";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__212 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__212> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5269 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1 = x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5270 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 5271 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 5272 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 5274 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 5275 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__212* storage = x10aux::alloc<Lib__closure__212>();
        buf.record_reference(x10aux::ref<Lib__closure__212>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__212> this_ = new (storage) Lib__closure__212(that_x, that_y);
        return this_;
    }
    
    Lib__closure__212(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5268-5276";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__211 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__211> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5243 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 5244 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 5245 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 5247 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 5248 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2 = x10aux::nullCheck(x10aux::nullCheck(y)->
                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5250 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__211* storage = x10aux::alloc<Lib__closure__211>();
        buf.record_reference(x10aux::ref<Lib__closure__211>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__211> this_ = new (storage) Lib__closure__211(that_x, that_y);
        return this_;
    }
    
    Lib__closure__211(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5242-5251";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__210 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__210> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5084 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1 = x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5085 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 5086 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 5087 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 5089 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 5090 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__210* storage = x10aux::alloc<Lib__closure__210>();
        buf.record_reference(x10aux::ref<Lib__closure__210>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__210> this_ = new (storage) Lib__closure__210(that_x, that_y);
        return this_;
    }
    
    Lib__closure__210(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5083-5091";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__209 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__209> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5058 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 5059 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 5060 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 5062 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 5063 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2 = x10aux::nullCheck(x10aux::nullCheck(y)->
                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)0)),
                           x10aux::nullCheck(p)->x10::array::Point::apply(
                             ((x10_int)1)));
        
        //#line 5065 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__209* storage = x10aux::alloc<Lib__closure__209>();
        buf.record_reference(x10aux::ref<Lib__closure__209>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__209> this_ = new (storage) Lib__closure__209(that_x, that_y);
        return this_;
    }
    
    Lib__closure__209(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5057-5066";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__208 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__208> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 5003 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 5004 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 5005 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 5007 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 5009 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__208* storage = x10aux::alloc<Lib__closure__208>();
        buf.record_reference(x10aux::ref<Lib__closure__208>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__208> this_ = new (storage) Lib__closure__208(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__208(x10aux::ref<DoubleMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:5001-5010";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__207 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__207> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4973 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4974 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4975 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4977 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4979 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__207* storage = x10aux::alloc<Lib__closure__207>();
        buf.record_reference(x10aux::ref<Lib__closure__207>(storage));
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__207> this_ = new (storage) Lib__closure__207(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__207(x10aux::ref<DoubleMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4971-4980";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__206 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__206> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4937 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4938 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4939 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4941 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4942 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4943 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4944 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4946 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4948 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__206* storage = x10aux::alloc<Lib__closure__206>();
        buf.record_reference(x10aux::ref<Lib__closure__206>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__206> this_ = new (storage) Lib__closure__206(that_x, that_y);
        return this_;
    }
    
    Lib__closure__206(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4934-4949";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__205 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__205> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4885 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4886 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4887 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4889 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4891 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__205* storage = x10aux::alloc<Lib__closure__205>();
        buf.record_reference(x10aux::ref<Lib__closure__205>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__205> this_ = new (storage) Lib__closure__205(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__205(x10aux::ref<DoubleMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4883-4892";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__204 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__204> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4855 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4856 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4857 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4859 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4861 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__204* storage = x10aux::alloc<Lib__closure__204>();
        buf.record_reference(x10aux::ref<Lib__closure__204>(storage));
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__204> this_ = new (storage) Lib__closure__204(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__204(x10aux::ref<DoubleMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4853-4862";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__203 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__203> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4819 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4820 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4821 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4823 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4824 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4825 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4826 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4828 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4830 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__203* storage = x10aux::alloc<Lib__closure__203>();
        buf.record_reference(x10aux::ref<Lib__closure__203>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__203> this_ = new (storage) Lib__closure__203(that_x, that_y);
        return this_;
    }
    
    Lib__closure__203(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4816-4831";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__202 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__202> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4767 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4768 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4769 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4771 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4773 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__202* storage = x10aux::alloc<Lib__closure__202>();
        buf.record_reference(x10aux::ref<Lib__closure__202>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__202> this_ = new (storage) Lib__closure__202(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__202(x10aux::ref<DoubleMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4765-4774";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__201 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__201> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4737 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4738 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4739 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4741 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4743 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__201* storage = x10aux::alloc<Lib__closure__201>();
        buf.record_reference(x10aux::ref<Lib__closure__201>(storage));
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__201> this_ = new (storage) Lib__closure__201(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__201(x10aux::ref<IntMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4735-4744";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__200 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__200> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4701 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4702 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4703 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4705 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4706 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4707 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4708 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4710 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4712 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__200* storage = x10aux::alloc<Lib__closure__200>();
        buf.record_reference(x10aux::ref<Lib__closure__200>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__200> this_ = new (storage) Lib__closure__200(that_x, that_y);
        return this_;
    }
    
    Lib__closure__200(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4698-4713";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__199 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__199> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4649 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4650 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4651 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4653 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4655 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__199* storage = x10aux::alloc<Lib__closure__199>();
        buf.record_reference(x10aux::ref<Lib__closure__199>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__199> this_ = new (storage) Lib__closure__199(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__199(x10aux::ref<DoubleMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4647-4656";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__198 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__198> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4619 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4620 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4621 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4623 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4625 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__198* storage = x10aux::alloc<Lib__closure__198>();
        buf.record_reference(x10aux::ref<Lib__closure__198>(storage));
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__198> this_ = new (storage) Lib__closure__198(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__198(x10aux::ref<IntMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4617-4626";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__197 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__197> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4583 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4584 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4585 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4587 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4588 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4589 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4590 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4592 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4594 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__197* storage = x10aux::alloc<Lib__closure__197>();
        buf.record_reference(x10aux::ref<Lib__closure__197>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__197> this_ = new (storage) Lib__closure__197(that_x, that_y);
        return this_;
    }
    
    Lib__closure__197(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4580-4595";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__196 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__196> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4531 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4532 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4533 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4535 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4537 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__196* storage = x10aux::alloc<Lib__closure__196>();
        buf.record_reference(x10aux::ref<Lib__closure__196>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__196> this_ = new (storage) Lib__closure__196(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__196(x10aux::ref<IntMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4529-4538";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__195 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__195> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4501 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4502 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4503 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4505 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4507 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__195* storage = x10aux::alloc<Lib__closure__195>();
        buf.record_reference(x10aux::ref<Lib__closure__195>(storage));
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__195> this_ = new (storage) Lib__closure__195(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__195(x10aux::ref<DoubleMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4499-4508";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__194 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__194> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4465 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4466 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4467 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4469 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4470 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4471 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4474 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4476 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__194* storage = x10aux::alloc<Lib__closure__194>();
        buf.record_reference(x10aux::ref<Lib__closure__194>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__194> this_ = new (storage) Lib__closure__194(that_x, that_y);
        return this_;
    }
    
    Lib__closure__194(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4462-4477";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__193 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__193> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4413 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4414 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4415 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4417 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4419 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__193* storage = x10aux::alloc<Lib__closure__193>();
        buf.record_reference(x10aux::ref<Lib__closure__193>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__193> this_ = new (storage) Lib__closure__193(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__193(x10aux::ref<IntMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4411-4420";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__192 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__192> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4383 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4384 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4385 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4387 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4389 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__192* storage = x10aux::alloc<Lib__closure__192>();
        buf.record_reference(x10aux::ref<Lib__closure__192>(storage));
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__192> this_ = new (storage) Lib__closure__192(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__192(x10aux::ref<DoubleMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4381-4390";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__191 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__191> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4347 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4348 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4349 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4351 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4352 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4353 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_double>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_double) (((x10_int)0))))))
        {
            
            //#line 4354 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4356 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4358 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__191* storage = x10aux::alloc<Lib__closure__191>();
        buf.record_reference(x10aux::ref<Lib__closure__191>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__191> this_ = new (storage) Lib__closure__191(that_x, that_y);
        return this_;
    }
    
    Lib__closure__191(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4344-4359";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__190 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__190> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4295 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4296 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4297 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4299 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4301 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__190* storage = x10aux::alloc<Lib__closure__190>();
        buf.record_reference(x10aux::ref<Lib__closure__190>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__190> this_ = new (storage) Lib__closure__190(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__190(x10aux::ref<IntMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4293-4302";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__189 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__189> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4265 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4266 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4267 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4269 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4271 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__189* storage = x10aux::alloc<Lib__closure__189>();
        buf.record_reference(x10aux::ref<Lib__closure__189>(storage));
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__189> this_ = new (storage) Lib__closure__189(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__189(x10aux::ref<IntMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4263-4272";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__188 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__188> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4229 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4230 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4231 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4233 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4234 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4235 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4236 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4238 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4240 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 || b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__188* storage = x10aux::alloc<Lib__closure__188>();
        buf.record_reference(x10aux::ref<Lib__closure__188>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__188> this_ = new (storage) Lib__closure__188(that_x, that_y);
        return this_;
    }
    
    Lib__closure__188(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4226-4241";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__187 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__187> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4177 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4178 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4179 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4181 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4183 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean b2;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->b2);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__187* storage = x10aux::alloc<Lib__closure__187>();
        buf.record_reference(x10aux::ref<Lib__closure__187>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b2 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__187> this_ = new (storage) Lib__closure__187(that_x, that_b2);
        return this_;
    }
    
    Lib__closure__187(x10aux::ref<IntMatrix> x, x10_boolean b2) : x(x), b2(b2) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4175-4184";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__186 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__186> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4147 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4148 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4149 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4151 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4153 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> y;
    x10_boolean b1;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->y);
        buf.write(this->b1);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__186* storage = x10aux::alloc<Lib__closure__186>();
        buf.record_reference(x10aux::ref<Lib__closure__186>(storage));
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_b1 = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__186> this_ = new (storage) Lib__closure__186(that_y, that_b1);
        return this_;
    }
    
    Lib__closure__186(x10aux::ref<IntMatrix> y, x10_boolean b1) : y(y), b1(b1) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4145-4154";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__185 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__185> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4111 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b1;
        
        //#line 4112 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4113 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              true;
        }
        else
        {
            
            //#line 4115 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b1 =
              false;
        }
        
        //#line 4116 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_boolean b2;
        
        //#line 4117 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(y)->
                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)0)),
                                      x10aux::nullCheck(p)->x10::array::Point::apply(
                                        ((x10_int)1))),
                                    ((x10_int)0))))
        {
            
            //#line 4118 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              true;
        }
        else
        {
            
            //#line 4120 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
            b2 =
              false;
        }
        
        //#line 4122 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b1 && b2;
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__185* storage = x10aux::alloc<Lib__closure__185>();
        buf.record_reference(x10aux::ref<Lib__closure__185>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__185> this_ = new (storage) Lib__closure__185(that_x, that_y);
        return this_;
    }
    
    Lib__closure__185(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4108-4123";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__184 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__184> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4067 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) >= (y
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__184* storage = x10aux::alloc<Lib__closure__184>();
        buf.record_reference(x10aux::ref<Lib__closure__184>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__184> this_ = new (storage) Lib__closure__184(that_x, that_y);
        return this_;
    }
    
    Lib__closure__184(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4066-4067";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__183 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__183> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4051 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)0)),
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__183* storage = x10aux::alloc<Lib__closure__183>();
        buf.record_reference(x10aux::ref<Lib__closure__183>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__183> this_ = new (storage) Lib__closure__183(that_x, that_y);
        return this_;
    }
    
    Lib__closure__183(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4050-4051";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__182 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__182> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4032 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                 FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)0)),
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__182* storage = x10aux::alloc<Lib__closure__182>();
        buf.record_reference(x10aux::ref<Lib__closure__182>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__182> this_ = new (storage) Lib__closure__182(that_x, that_y);
        return this_;
    }
    
    Lib__closure__182(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4031-4032";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__181 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__181> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 4001 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (y
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__181* storage = x10aux::alloc<Lib__closure__181>();
        buf.record_reference(x10aux::ref<Lib__closure__181>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__181> this_ = new (storage) Lib__closure__181(that_x, that_y);
        return this_;
    }
    
    Lib__closure__181(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:4000-4001";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__180 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__180> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3985 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                               FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)0)),
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__180* storage = x10aux::alloc<Lib__closure__180>();
        buf.record_reference(x10aux::ref<Lib__closure__180>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__180> this_ = new (storage) Lib__closure__180(that_x, that_y);
        return this_;
    }
    
    Lib__closure__180(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3984-3985";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__179 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__179> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3966 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__179* storage = x10aux::alloc<Lib__closure__179>();
        buf.record_reference(x10aux::ref<Lib__closure__179>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__179> this_ = new (storage) Lib__closure__179(that_x, that_y);
        return this_;
    }
    
    Lib__closure__179(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3965-3966";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__178 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__178> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3935 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (y
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__178* storage = x10aux::alloc<Lib__closure__178>();
        buf.record_reference(x10aux::ref<Lib__closure__178>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__178> this_ = new (storage) Lib__closure__178(that_x, that_y);
        return this_;
    }
    
    Lib__closure__178(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3934-3935";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__177 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__177> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3919 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)0)),
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__177* storage = x10aux::alloc<Lib__closure__177>();
        buf.record_reference(x10aux::ref<Lib__closure__177>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__177> this_ = new (storage) Lib__closure__177(that_x, that_y);
        return this_;
    }
    
    Lib__closure__177(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3918-3919";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__176 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__176> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3900 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                 FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)0)),
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__176* storage = x10aux::alloc<Lib__closure__176>();
        buf.record_reference(x10aux::ref<Lib__closure__176>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__176> this_ = new (storage) Lib__closure__176(that_x, that_y);
        return this_;
    }
    
    Lib__closure__176(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3899-3900";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__175 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__175> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3869 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (y
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__175* storage = x10aux::alloc<Lib__closure__175>();
        buf.record_reference(x10aux::ref<Lib__closure__175>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__175> this_ = new (storage) Lib__closure__175(that_x, that_y);
        return this_;
    }
    
    Lib__closure__175(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3868-3869";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__174 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__174> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3853 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                               FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)0)),
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__174* storage = x10aux::alloc<Lib__closure__174>();
        buf.record_reference(x10aux::ref<Lib__closure__174>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__174> this_ = new (storage) Lib__closure__174(that_x, that_y);
        return this_;
    }
    
    Lib__closure__174(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3852-3853";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__173 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__173> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3834 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__173* storage = x10aux::alloc<Lib__closure__173>();
        buf.record_reference(x10aux::ref<Lib__closure__173>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__173> this_ = new (storage) Lib__closure__173(that_x, that_y);
        return this_;
    }
    
    Lib__closure__173(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3833-3834";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__172 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__172> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3799 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (((x10_double) (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__172* storage = x10aux::alloc<Lib__closure__172>();
        buf.record_reference(x10aux::ref<Lib__closure__172>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__172> this_ = new (storage) Lib__closure__172(that_x, that_y);
        return this_;
    }
    
    Lib__closure__172(x10aux::ref<DoubleMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3798-3799";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__171 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__171> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3784 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) > (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__171* storage = x10aux::alloc<Lib__closure__171>();
        buf.record_reference(x10aux::ref<Lib__closure__171>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__171> this_ = new (storage) Lib__closure__171(that_x, that_y);
        return this_;
    }
    
    Lib__closure__171(x10aux::ref<BooleanMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3783-3784";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__170 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__170> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3768 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) > (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__170* storage = x10aux::alloc<Lib__closure__170>();
        buf.record_reference(x10aux::ref<Lib__closure__170>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__170> this_ = new (storage) Lib__closure__170(that_x, that_y);
        return this_;
    }
    
    Lib__closure__170(x10_double x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3767-3768";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__169 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__169> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3753 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_double>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__169* storage = x10aux::alloc<Lib__closure__169>();
        buf.record_reference(x10aux::ref<Lib__closure__169>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__169> this_ = new (storage) Lib__closure__169(that_x, that_y);
        return this_;
    }
    
    Lib__closure__169(x10_boolean x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3752-3753";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__168 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__168> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3736 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__168* storage = x10aux::alloc<Lib__closure__168>();
        buf.record_reference(x10aux::ref<Lib__closure__168>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__168> this_ = new (storage) Lib__closure__168(that_x, that_y);
        return this_;
    }
    
    Lib__closure__168(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3735-3736";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__167 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__167> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3718 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__167* storage = x10aux::alloc<Lib__closure__167>();
        buf.record_reference(x10aux::ref<Lib__closure__167>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__167> this_ = new (storage) Lib__closure__167(that_x, that_y);
        return this_;
    }
    
    Lib__closure__167(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3717-3718";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__166 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__166> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3678 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (((x10_double) (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__166* storage = x10aux::alloc<Lib__closure__166>();
        buf.record_reference(x10aux::ref<Lib__closure__166>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__166> this_ = new (storage) Lib__closure__166(that_x, that_y);
        return this_;
    }
    
    Lib__closure__166(x10aux::ref<DoubleMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3677-3678";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__165 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__165> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3663 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) <= (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__165* storage = x10aux::alloc<Lib__closure__165>();
        buf.record_reference(x10aux::ref<Lib__closure__165>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__165> this_ = new (storage) Lib__closure__165(that_x, that_y);
        return this_;
    }
    
    Lib__closure__165(x10aux::ref<BooleanMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3662-3663";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__164 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__164> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3647 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) <= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__164* storage = x10aux::alloc<Lib__closure__164>();
        buf.record_reference(x10aux::ref<Lib__closure__164>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__164> this_ = new (storage) Lib__closure__164(that_x, that_y);
        return this_;
    }
    
    Lib__closure__164(x10_double x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3646-3647";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__163 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__163> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3632 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                 FMGL(array))->x10::array::Array<x10_double>::apply(
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)0)),
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__163* storage = x10aux::alloc<Lib__closure__163>();
        buf.record_reference(x10aux::ref<Lib__closure__163>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__163> this_ = new (storage) Lib__closure__163(that_x, that_y);
        return this_;
    }
    
    Lib__closure__163(x10_boolean x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3631-3632";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__162 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__162> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3615 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__162* storage = x10aux::alloc<Lib__closure__162>();
        buf.record_reference(x10aux::ref<Lib__closure__162>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__162> this_ = new (storage) Lib__closure__162(that_x, that_y);
        return this_;
    }
    
    Lib__closure__162(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3614-3615";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__161 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__161> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3597 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                               FMGL(array))->x10::array::Array<x10_double>::apply(
                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                               ((x10_int)0)),
                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                               ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__161* storage = x10aux::alloc<Lib__closure__161>();
        buf.record_reference(x10aux::ref<Lib__closure__161>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__161> this_ = new (storage) Lib__closure__161(that_x, that_y);
        return this_;
    }
    
    Lib__closure__161(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3596-3597";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__160 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__160> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3557 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (((x10_double) (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__160* storage = x10aux::alloc<Lib__closure__160>();
        buf.record_reference(x10aux::ref<Lib__closure__160>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__160> this_ = new (storage) Lib__closure__160(that_x, that_y);
        return this_;
    }
    
    Lib__closure__160(x10aux::ref<DoubleMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3556-3557";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__159 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__159> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3542 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) < (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__159* storage = x10aux::alloc<Lib__closure__159>();
        buf.record_reference(x10aux::ref<Lib__closure__159>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__159> this_ = new (storage) Lib__closure__159(that_x, that_y);
        return this_;
    }
    
    Lib__closure__159(x10aux::ref<BooleanMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3541-3542";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__158 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__158> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3526 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) < (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__158* storage = x10aux::alloc<Lib__closure__158>();
        buf.record_reference(x10aux::ref<Lib__closure__158>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__158> this_ = new (storage) Lib__closure__158(that_x, that_y);
        return this_;
    }
    
    Lib__closure__158(x10_double x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3525-3526";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__157 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__157> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3511 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_double>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__157* storage = x10aux::alloc<Lib__closure__157>();
        buf.record_reference(x10aux::ref<Lib__closure__157>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__157> this_ = new (storage) Lib__closure__157(that_x, that_y);
        return this_;
    }
    
    Lib__closure__157(x10_boolean x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3510-3511";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__156 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__156> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3494 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__156* storage = x10aux::alloc<Lib__closure__156>();
        buf.record_reference(x10aux::ref<Lib__closure__156>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__156> this_ = new (storage) Lib__closure__156(that_x, that_y);
        return this_;
    }
    
    Lib__closure__156(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3493-3494";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__155 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__155> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3476 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__155* storage = x10aux::alloc<Lib__closure__155>();
        buf.record_reference(x10aux::ref<Lib__closure__155>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__155> this_ = new (storage) Lib__closure__155(that_x, that_y);
        return this_;
    }
    
    Lib__closure__155(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3475-3476";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__154 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__154> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3436 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_double) (y
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__154* storage = x10aux::alloc<Lib__closure__154>();
        buf.record_reference(x10aux::ref<Lib__closure__154>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__154> this_ = new (storage) Lib__closure__154(that_x, that_y);
        return this_;
    }
    
    Lib__closure__154(x10aux::ref<DoubleMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3435-3436";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__153 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__153> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3421 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0)))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__153* storage = x10aux::alloc<Lib__closure__153>();
        buf.record_reference(x10aux::ref<Lib__closure__153>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__153> this_ = new (storage) Lib__closure__153(that_x, that_y);
        return this_;
    }
    
    Lib__closure__153(x10aux::ref<BooleanMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3420-3421";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__152 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__152> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3405 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__152* storage = x10aux::alloc<Lib__closure__152>();
        buf.record_reference(x10aux::ref<Lib__closure__152>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__152> this_ = new (storage) Lib__closure__152(that_x, that_y);
        return this_;
    }
    
    Lib__closure__152(x10_double x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3404-3405";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__151 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__151> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3390 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0)))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__151* storage = x10aux::alloc<Lib__closure__151>();
        buf.record_reference(x10aux::ref<Lib__closure__151>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__151> this_ = new (storage) Lib__closure__151(that_x, that_y);
        return this_;
    }
    
    Lib__closure__151(x10_boolean x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3389-3390";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__150 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__150> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3373 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__150* storage = x10aux::alloc<Lib__closure__150>();
        buf.record_reference(x10aux::ref<Lib__closure__150>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__150> this_ = new (storage) Lib__closure__150(that_x, that_y);
        return this_;
    }
    
    Lib__closure__150(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3372-3373";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__149 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__149> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3355 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0)))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__149* storage = x10aux::alloc<Lib__closure__149>();
        buf.record_reference(x10aux::ref<Lib__closure__149>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__149> this_ = new (storage) Lib__closure__149(that_x, that_y);
        return this_;
    }
    
    Lib__closure__149(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3354-3355";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__148 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__148> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3315 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      ((x10_double) (y
                                        ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__148* storage = x10aux::alloc<Lib__closure__148>();
        buf.record_reference(x10aux::ref<Lib__closure__148>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__148> this_ = new (storage) Lib__closure__148(that_x, that_y);
        return this_;
    }
    
    Lib__closure__148(x10aux::ref<DoubleMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3314-3315";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__147 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__147> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3300 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                         FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1)))
                                        ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0)))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__147* storage = x10aux::alloc<Lib__closure__147>();
        buf.record_reference(x10aux::ref<Lib__closure__147>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__147> this_ = new (storage) Lib__closure__147(that_x, that_y);
        return this_;
    }
    
    Lib__closure__147(x10aux::ref<BooleanMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3299-3300";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__146 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__146> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3284 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1)))
                                        ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__146* storage = x10aux::alloc<Lib__closure__146>();
        buf.record_reference(x10aux::ref<Lib__closure__146>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__146> this_ = new (storage) Lib__closure__146(that_x, that_y);
        return this_;
    }
    
    Lib__closure__146(x10_double x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3283-3284";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__145 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__145> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3269 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0)))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__145* storage = x10aux::alloc<Lib__closure__145>();
        buf.record_reference(x10aux::ref<Lib__closure__145>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__145> this_ = new (storage) Lib__closure__145(that_x, that_y);
        return this_;
    }
    
    Lib__closure__145(x10_boolean x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3268-3269";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__144 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__144> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3252 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                         FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1)))
                                        ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__144* storage = x10aux::alloc<Lib__closure__144>();
        buf.record_reference(x10aux::ref<Lib__closure__144>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__144> this_ = new (storage) Lib__closure__144(that_x, that_y);
        return this_;
    }
    
    Lib__closure__144(x10aux::ref<DoubleMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3251-3252";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__143 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__143> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3234 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                         FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1)))
                                        ? (x10_int)(((x10_int)1))
                                        : (x10_int)(((x10_int)0)))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__143* storage = x10aux::alloc<Lib__closure__143>();
        buf.record_reference(x10aux::ref<Lib__closure__143>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__143> this_ = new (storage) Lib__closure__143(that_x, that_y);
        return this_;
    }
    
    Lib__closure__143(x10aux::ref<BooleanMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3233-3234";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__142 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__142> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3194 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__142* storage = x10aux::alloc<Lib__closure__142>();
        buf.record_reference(x10aux::ref<Lib__closure__142>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__142> this_ = new (storage) Lib__closure__142(that_x, that_y);
        return this_;
    }
    
    Lib__closure__142(x10aux::ref<IntMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3193-3194";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__141 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__141> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3179 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__141* storage = x10aux::alloc<Lib__closure__141>();
        buf.record_reference(x10aux::ref<Lib__closure__141>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__141> this_ = new (storage) Lib__closure__141(that_x, that_y);
        return this_;
    }
    
    Lib__closure__141(x10aux::ref<BooleanMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3178-3179";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__140 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__140> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3163 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) > (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__140* storage = x10aux::alloc<Lib__closure__140>();
        buf.record_reference(x10aux::ref<Lib__closure__140>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__140> this_ = new (storage) Lib__closure__140(that_x, that_y);
        return this_;
    }
    
    Lib__closure__140(x10_int x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3162-3163";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__139 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__139> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3148 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)0)),
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__139* storage = x10aux::alloc<Lib__closure__139>();
        buf.record_reference(x10aux::ref<Lib__closure__139>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__139> this_ = new (storage) Lib__closure__139(that_x, that_y);
        return this_;
    }
    
    Lib__closure__139(x10_boolean x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3147-3148";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__138 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__138> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3131 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__138* storage = x10aux::alloc<Lib__closure__138>();
        buf.record_reference(x10aux::ref<Lib__closure__138>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__138> this_ = new (storage) Lib__closure__138(that_x, that_y);
        return this_;
    }
    
    Lib__closure__138(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3130-3131";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__137 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__137> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3113 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__137* storage = x10aux::alloc<Lib__closure__137>();
        buf.record_reference(x10aux::ref<Lib__closure__137>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__137> this_ = new (storage) Lib__closure__137(that_x, that_y);
        return this_;
    }
    
    Lib__closure__137(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3112-3113";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__136 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__136> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3073 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__136* storage = x10aux::alloc<Lib__closure__136>();
        buf.record_reference(x10aux::ref<Lib__closure__136>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__136> this_ = new (storage) Lib__closure__136(that_x, that_y);
        return this_;
    }
    
    Lib__closure__136(x10aux::ref<IntMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3072-3073";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__135 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__135> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3058 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__135* storage = x10aux::alloc<Lib__closure__135>();
        buf.record_reference(x10aux::ref<Lib__closure__135>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__135> this_ = new (storage) Lib__closure__135(that_x, that_y);
        return this_;
    }
    
    Lib__closure__135(x10aux::ref<BooleanMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3057-3058";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__134 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__134> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3042 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) <= (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)0)),
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__134* storage = x10aux::alloc<Lib__closure__134>();
        buf.record_reference(x10aux::ref<Lib__closure__134>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__134> this_ = new (storage) Lib__closure__134(that_x, that_y);
        return this_;
    }
    
    Lib__closure__134(x10_int x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3041-3042";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__133 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__133> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3027 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)0)),
                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__133* storage = x10aux::alloc<Lib__closure__133>();
        buf.record_reference(x10aux::ref<Lib__closure__133>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__133> this_ = new (storage) Lib__closure__133(that_x, that_y);
        return this_;
    }
    
    Lib__closure__133(x10_boolean x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3026-3027";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__132 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__132> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 3010 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__132* storage = x10aux::alloc<Lib__closure__132>();
        buf.record_reference(x10aux::ref<Lib__closure__132>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__132> this_ = new (storage) Lib__closure__132(that_x, that_y);
        return this_;
    }
    
    Lib__closure__132(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:3009-3010";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__131 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__131> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2992 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                 FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)0)),
                                                                                               x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                 ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__131* storage = x10aux::alloc<Lib__closure__131>();
        buf.record_reference(x10aux::ref<Lib__closure__131>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__131> this_ = new (storage) Lib__closure__131(that_x, that_y);
        return this_;
    }
    
    Lib__closure__131(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2991-2992";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__130 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__130> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2952 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (y ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__130* storage = x10aux::alloc<Lib__closure__130>();
        buf.record_reference(x10aux::ref<Lib__closure__130>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__130> this_ = new (storage) Lib__closure__130(that_x, that_y);
        return this_;
    }
    
    Lib__closure__130(x10aux::ref<IntMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2951-2952";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__129 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__129> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2937 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__129* storage = x10aux::alloc<Lib__closure__129>();
        buf.record_reference(x10aux::ref<Lib__closure__129>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__129> this_ = new (storage) Lib__closure__129(that_x, that_y);
        return this_;
    }
    
    Lib__closure__129(x10aux::ref<BooleanMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2936-2937";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__128 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__128> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2921 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) < (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1))) ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__128* storage = x10aux::alloc<Lib__closure__128>();
        buf.record_reference(x10aux::ref<Lib__closure__128>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__128> this_ = new (storage) Lib__closure__128(that_x, that_y);
        return this_;
    }
    
    Lib__closure__128(x10_int x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2920-2921";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__127 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__127> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2906 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)0)),
                                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                               ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__127* storage = x10aux::alloc<Lib__closure__127>();
        buf.record_reference(x10aux::ref<Lib__closure__127>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__127> this_ = new (storage) Lib__closure__127(that_x, that_y);
        return this_;
    }
    
    Lib__closure__127(x10_boolean x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2905-2906";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__126 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__126> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2889 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))
          ? (x10_int)(((x10_int)1))
          : (x10_int)(((x10_int)0))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__126* storage = x10aux::alloc<Lib__closure__126>();
        buf.record_reference(x10aux::ref<Lib__closure__126>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__126> this_ = new (storage) Lib__closure__126(that_x, that_y);
        return this_;
    }
    
    Lib__closure__126(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2888-2889";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__125 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__125> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2871 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1))) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                                                                FMGL(array))->x10::array::Array<x10_int>::apply(
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)0)),
                                                                                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                                                                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__125* storage = x10aux::alloc<Lib__closure__125>();
        buf.record_reference(x10aux::ref<Lib__closure__125>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__125> this_ = new (storage) Lib__closure__125(that_x, that_y);
        return this_;
    }
    
    Lib__closure__125(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2870-2871";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__124 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__124> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2831 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       (y
                                          ? (x10_int)(((x10_int)1))
                                          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__124* storage = x10aux::alloc<Lib__closure__124>();
        buf.record_reference(x10aux::ref<Lib__closure__124>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__124> this_ = new (storage) Lib__closure__124(that_x, that_y);
        return this_;
    }
    
    Lib__closure__124(x10aux::ref<IntMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2830-2831";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__123 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__123> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2816 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals((x10aux::nullCheck(x10aux::nullCheck(x)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))
                                          ? (x10_int)(((x10_int)1))
                                          : (x10_int)(((x10_int)0))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__123* storage = x10aux::alloc<Lib__closure__123>();
        buf.record_reference(x10aux::ref<Lib__closure__123>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__123> this_ = new (storage) Lib__closure__123(that_x, that_y);
        return this_;
    }
    
    Lib__closure__123(x10aux::ref<BooleanMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2815-2816";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__122 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__122> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2800 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                               FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                               ((x10_int)0)),
                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                               ((x10_int)1)))
                                             ? (x10_int)(((x10_int)1))
                                             : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__122* storage = x10aux::alloc<Lib__closure__122>();
        buf.record_reference(x10aux::ref<Lib__closure__122>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__122> this_ = new (storage) Lib__closure__122(that_x, that_y);
        return this_;
    }
    
    Lib__closure__122(x10_int x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2799-2800";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__121 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__121> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2785 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__121* storage = x10aux::alloc<Lib__closure__121>();
        buf.record_reference(x10aux::ref<Lib__closure__121>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__121> this_ = new (storage) Lib__closure__121(that_x, that_y);
        return this_;
    }
    
    Lib__closure__121(x10_boolean x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2784-2785";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__120 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__120> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2768 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))
                                          ? (x10_int)(((x10_int)1))
                                          : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__120* storage = x10aux::alloc<Lib__closure__120>();
        buf.record_reference(x10aux::ref<Lib__closure__120>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__120> this_ = new (storage) Lib__closure__120(that_x, that_y);
        return this_;
    }
    
    Lib__closure__120(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2767-2768";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__119 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__119> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2750 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals((x10aux::nullCheck(x10aux::nullCheck(x)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))
                                          ? (x10_int)(((x10_int)1))
                                          : (x10_int)(((x10_int)0))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__119* storage = x10aux::alloc<Lib__closure__119>();
        buf.record_reference(x10aux::ref<Lib__closure__119>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__119> this_ = new (storage) Lib__closure__119(that_x, that_y);
        return this_;
    }
    
    Lib__closure__119(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2749-2750";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__118 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__118> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2710 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      (y
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__118* storage = x10aux::alloc<Lib__closure__118>();
        buf.record_reference(x10aux::ref<Lib__closure__118>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__118> this_ = new (storage) Lib__closure__118(that_x, that_y);
        return this_;
    }
    
    Lib__closure__118(x10aux::ref<IntMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2709-2710";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__117 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__117> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2695 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals((x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__117* storage = x10aux::alloc<Lib__closure__117>();
        buf.record_reference(x10aux::ref<Lib__closure__117>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__117> this_ = new (storage) Lib__closure__117(that_x, that_y);
        return this_;
    }
    
    Lib__closure__117(x10aux::ref<BooleanMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2694-2695";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__116 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__116> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2679 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))
                                            ? (x10_int)(((x10_int)1))
                                            : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__116* storage = x10aux::alloc<Lib__closure__116>();
        buf.record_reference(x10aux::ref<Lib__closure__116>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__116> this_ = new (storage) Lib__closure__116(that_x, that_y);
        return this_;
    }
    
    Lib__closure__116(x10_int x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2678-2679";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__115 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__115> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2664 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals((x ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__115* storage = x10aux::alloc<Lib__closure__115>();
        buf.record_reference(x10aux::ref<Lib__closure__115>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__115> this_ = new (storage) Lib__closure__115(that_x, that_y);
        return this_;
    }
    
    Lib__closure__115(x10_boolean x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2663-2664";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__114 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__114> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2647 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__114* storage = x10aux::alloc<Lib__closure__114>();
        buf.record_reference(x10aux::ref<Lib__closure__114>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__114> this_ = new (storage) Lib__closure__114(that_x, that_y);
        return this_;
    }
    
    Lib__closure__114(x10aux::ref<IntMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2646-2647";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__113 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__113> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2629 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals((x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))
                                         ? (x10_int)(((x10_int)1))
                                         : (x10_int)(((x10_int)0))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__113* storage = x10aux::alloc<Lib__closure__113>();
        buf.record_reference(x10aux::ref<Lib__closure__113>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__113> this_ = new (storage) Lib__closure__113(that_x, that_y);
        return this_;
    }
    
    Lib__closure__113(x10aux::ref<BooleanMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2628-2629";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__112 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__112> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2593 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__112* storage = x10aux::alloc<Lib__closure__112>();
        buf.record_reference(x10aux::ref<Lib__closure__112>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__112> this_ = new (storage) Lib__closure__112(that_x, that_y);
        return this_;
    }
    
    Lib__closure__112(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2592-2593";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__111 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__111> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2577 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) >= (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)0)),
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__111* storage = x10aux::alloc<Lib__closure__111>();
        buf.record_reference(x10aux::ref<Lib__closure__111>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__111> this_ = new (storage) Lib__closure__111(that_x, that_y);
        return this_;
    }
    
    Lib__closure__111(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2576-2577";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__110 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__110> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2560 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__110* storage = x10aux::alloc<Lib__closure__110>();
        buf.record_reference(x10aux::ref<Lib__closure__110>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__110> this_ = new (storage) Lib__closure__110(that_x, that_y);
        return this_;
    }
    
    Lib__closure__110(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2559-2560";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__109 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__109> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2531 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__109* storage = x10aux::alloc<Lib__closure__109>();
        buf.record_reference(x10aux::ref<Lib__closure__109>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__109> this_ = new (storage) Lib__closure__109(that_x, that_y);
        return this_;
    }
    
    Lib__closure__109(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2530-2531";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__108 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__108> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2515 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) > (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__108* storage = x10aux::alloc<Lib__closure__108>();
        buf.record_reference(x10aux::ref<Lib__closure__108>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__108> this_ = new (storage) Lib__closure__108(that_x, that_y);
        return this_;
    }
    
    Lib__closure__108(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2514-2515";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__107 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__107> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2498 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__107* storage = x10aux::alloc<Lib__closure__107>();
        buf.record_reference(x10aux::ref<Lib__closure__107>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__107> this_ = new (storage) Lib__closure__107(that_x, that_y);
        return this_;
    }
    
    Lib__closure__107(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2497-2498";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__106 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__106> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2469 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__106* storage = x10aux::alloc<Lib__closure__106>();
        buf.record_reference(x10aux::ref<Lib__closure__106>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__106> this_ = new (storage) Lib__closure__106(that_x, that_y);
        return this_;
    }
    
    Lib__closure__106(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2468-2469";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__105 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__105> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2453 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) <= (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)0)),
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__105* storage = x10aux::alloc<Lib__closure__105>();
        buf.record_reference(x10aux::ref<Lib__closure__105>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__105> this_ = new (storage) Lib__closure__105(that_x, that_y);
        return this_;
    }
    
    Lib__closure__105(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2452-2453";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__104 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__104> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2436 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__104* storage = x10aux::alloc<Lib__closure__104>();
        buf.record_reference(x10aux::ref<Lib__closure__104>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__104> this_ = new (storage) Lib__closure__104(that_x, that_y);
        return this_;
    }
    
    Lib__closure__104(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2435-2436";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__103 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__103> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2407 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__103* storage = x10aux::alloc<Lib__closure__103>();
        buf.record_reference(x10aux::ref<Lib__closure__103>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__103> this_ = new (storage) Lib__closure__103(that_x, that_y);
        return this_;
    }
    
    Lib__closure__103(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2406-2407";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__102 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__102> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2391 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) < (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__102* storage = x10aux::alloc<Lib__closure__102>();
        buf.record_reference(x10aux::ref<Lib__closure__102>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__102> this_ = new (storage) Lib__closure__102(that_x, that_y);
        return this_;
    }
    
    Lib__closure__102(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2390-2391";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__101 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__101> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2374 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__101* storage = x10aux::alloc<Lib__closure__101>();
        buf.record_reference(x10aux::ref<Lib__closure__101>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__101> this_ = new (storage) Lib__closure__101(that_x, that_y);
        return this_;
    }
    
    Lib__closure__101(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2373-2374";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__100 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__100> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2345 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__100* storage = x10aux::alloc<Lib__closure__100>();
        buf.record_reference(x10aux::ref<Lib__closure__100>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__100> this_ = new (storage) Lib__closure__100(that_x, that_y);
        return this_;
    }
    
    Lib__closure__100(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2344-2345";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__99 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__99> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2329 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__99* storage = x10aux::alloc<Lib__closure__99>();
        buf.record_reference(x10aux::ref<Lib__closure__99>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__99> this_ = new (storage) Lib__closure__99(that_x, that_y);
        return this_;
    }
    
    Lib__closure__99(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2328-2329";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__98 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__98> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2312 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__98* storage = x10aux::alloc<Lib__closure__98>();
        buf.record_reference(x10aux::ref<Lib__closure__98>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__98> this_ = new (storage) Lib__closure__98(that_x, that_y);
        return this_;
    }
    
    Lib__closure__98(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2311-2312";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__97 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__97> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2283 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__97* storage = x10aux::alloc<Lib__closure__97>();
        buf.record_reference(x10aux::ref<Lib__closure__97>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__97> this_ = new (storage) Lib__closure__97(that_x, that_y);
        return this_;
    }
    
    Lib__closure__97(x10aux::ref<DoubleMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2282-2283";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__96 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__96> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2267 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__96* storage = x10aux::alloc<Lib__closure__96>();
        buf.record_reference(x10aux::ref<Lib__closure__96>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__96> this_ = new (storage) Lib__closure__96(that_x, that_y);
        return this_;
    }
    
    Lib__closure__96(x10_double x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2266-2267";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__95 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__95> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2250 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__95* storage = x10aux::alloc<Lib__closure__95>();
        buf.record_reference(x10aux::ref<Lib__closure__95>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__95> this_ = new (storage) Lib__closure__95(that_x, that_y);
        return this_;
    }
    
    Lib__closure__95(x10aux::ref<DoubleMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2249-2250";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__94 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__94> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2221 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__94* storage = x10aux::alloc<Lib__closure__94>();
        buf.record_reference(x10aux::ref<Lib__closure__94>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__94> this_ = new (storage) Lib__closure__94(that_x, that_y);
        return this_;
    }
    
    Lib__closure__94(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2220-2221";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__93 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__93> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2205 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) > (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__93* storage = x10aux::alloc<Lib__closure__93>();
        buf.record_reference(x10aux::ref<Lib__closure__93>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__93> this_ = new (storage) Lib__closure__93(that_x, that_y);
        return this_;
    }
    
    Lib__closure__93(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2204-2205";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__92 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__92> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2188 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__92* storage = x10aux::alloc<Lib__closure__92>();
        buf.record_reference(x10aux::ref<Lib__closure__92>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__92> this_ = new (storage) Lib__closure__92(that_x, that_y);
        return this_;
    }
    
    Lib__closure__92(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2187-2188";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__91 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__91> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2159 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__91* storage = x10aux::alloc<Lib__closure__91>();
        buf.record_reference(x10aux::ref<Lib__closure__91>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__91> this_ = new (storage) Lib__closure__91(that_x, that_y);
        return this_;
    }
    
    Lib__closure__91(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2158-2159";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__90 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__90> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2143 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) >= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__90* storage = x10aux::alloc<Lib__closure__90>();
        buf.record_reference(x10aux::ref<Lib__closure__90>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__90> this_ = new (storage) Lib__closure__90(that_x, that_y);
        return this_;
    }
    
    Lib__closure__90(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2142-2143";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__89 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__89> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2126 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__89* storage = x10aux::alloc<Lib__closure__89>();
        buf.record_reference(x10aux::ref<Lib__closure__89>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__89> this_ = new (storage) Lib__closure__89(that_x, that_y);
        return this_;
    }
    
    Lib__closure__89(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2125-2126";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__88 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__88> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2097 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__88* storage = x10aux::alloc<Lib__closure__88>();
        buf.record_reference(x10aux::ref<Lib__closure__88>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__88> this_ = new (storage) Lib__closure__88(that_x, that_y);
        return this_;
    }
    
    Lib__closure__88(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2096-2097";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__87 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__87> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2081 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) <= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__87* storage = x10aux::alloc<Lib__closure__87>();
        buf.record_reference(x10aux::ref<Lib__closure__87>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__87> this_ = new (storage) Lib__closure__87(that_x, that_y);
        return this_;
    }
    
    Lib__closure__87(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2080-2081";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__86 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__86> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2064 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__86* storage = x10aux::alloc<Lib__closure__86>();
        buf.record_reference(x10aux::ref<Lib__closure__86>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__86> this_ = new (storage) Lib__closure__86(that_x, that_y);
        return this_;
    }
    
    Lib__closure__86(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2063-2064";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__85 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__85> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2035 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__85* storage = x10aux::alloc<Lib__closure__85>();
        buf.record_reference(x10aux::ref<Lib__closure__85>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__85> this_ = new (storage) Lib__closure__85(that_x, that_y);
        return this_;
    }
    
    Lib__closure__85(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2034-2035";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__84 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__84> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2019 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) < (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__84* storage = x10aux::alloc<Lib__closure__84>();
        buf.record_reference(x10aux::ref<Lib__closure__84>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__84> this_ = new (storage) Lib__closure__84(that_x, that_y);
        return this_;
    }
    
    Lib__closure__84(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2018-2019";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__83 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__83> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 2002 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)0)),
                                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                                           ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__83* storage = x10aux::alloc<Lib__closure__83>();
        buf.record_reference(x10aux::ref<Lib__closure__83>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__83> this_ = new (storage) Lib__closure__83(that_x, that_y);
        return this_;
    }
    
    Lib__closure__83(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:2001-2002";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__82 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__82> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1973 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__82* storage = x10aux::alloc<Lib__closure__82>();
        buf.record_reference(x10aux::ref<Lib__closure__82>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__82> this_ = new (storage) Lib__closure__82(that_x, that_y);
        return this_;
    }
    
    Lib__closure__82(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1972-1973";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__81 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__81> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1957 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_int>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__81* storage = x10aux::alloc<Lib__closure__81>();
        buf.record_reference(x10aux::ref<Lib__closure__81>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__81> this_ = new (storage) Lib__closure__81(that_x, that_y);
        return this_;
    }
    
    Lib__closure__81(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1956-1957";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__80 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__80> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1940 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__80* storage = x10aux::alloc<Lib__closure__80>();
        buf.record_reference(x10aux::ref<Lib__closure__80>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__80> this_ = new (storage) Lib__closure__80(that_x, that_y);
        return this_;
    }
    
    Lib__closure__80(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1939-1940";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__79 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__79> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1911 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      ((x10_double) (y))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__79* storage = x10aux::alloc<Lib__closure__79>();
        buf.record_reference(x10aux::ref<Lib__closure__79>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__79> this_ = new (storage) Lib__closure__79(that_x, that_y);
        return this_;
    }
    
    Lib__closure__79(x10aux::ref<DoubleMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1910-1911";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__78 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__78> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1895 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)0)),
                                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                                            ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10_double x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__78* storage = x10aux::alloc<Lib__closure__78>();
        buf.record_reference(x10aux::ref<Lib__closure__78>(storage));
        x10_double that_x = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__78> this_ = new (storage) Lib__closure__78(that_x, that_y);
        return this_;
    }
    
    Lib__closure__78(x10_double x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1894-1895";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__77 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__77> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1878 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      ((x10_double) (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                         FMGL(array))->x10::array::Array<x10_int>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__77* storage = x10aux::alloc<Lib__closure__77>();
        buf.record_reference(x10aux::ref<Lib__closure__77>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__77> this_ = new (storage) Lib__closure__77(that_x, that_y);
        return this_;
    }
    
    Lib__closure__77(x10aux::ref<DoubleMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1877-1878";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__76 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__76> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1849 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) > (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__76* storage = x10aux::alloc<Lib__closure__76>();
        buf.record_reference(x10aux::ref<Lib__closure__76>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__76> this_ = new (storage) Lib__closure__76(that_x, that_y);
        return this_;
    }
    
    Lib__closure__76(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1848-1849";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__75 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__75> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1833 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__75* storage = x10aux::alloc<Lib__closure__75>();
        buf.record_reference(x10aux::ref<Lib__closure__75>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__75> this_ = new (storage) Lib__closure__75(that_x, that_y);
        return this_;
    }
    
    Lib__closure__75(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1832-1833";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__74 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__74> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1816 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__74* storage = x10aux::alloc<Lib__closure__74>();
        buf.record_reference(x10aux::ref<Lib__closure__74>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__74> this_ = new (storage) Lib__closure__74(that_x, that_y);
        return this_;
    }
    
    Lib__closure__74(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1815-1816";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__73 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__73> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1787 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) >= (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__73* storage = x10aux::alloc<Lib__closure__73>();
        buf.record_reference(x10aux::ref<Lib__closure__73>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__73> this_ = new (storage) Lib__closure__73(that_x, that_y);
        return this_;
    }
    
    Lib__closure__73(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1786-1787";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__72 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__72> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1771 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__72* storage = x10aux::alloc<Lib__closure__72>();
        buf.record_reference(x10aux::ref<Lib__closure__72>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__72> this_ = new (storage) Lib__closure__72(that_x, that_y);
        return this_;
    }
    
    Lib__closure__72(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1770-1771";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__71 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__71> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1754 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)0)),
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__71* storage = x10aux::alloc<Lib__closure__71>();
        buf.record_reference(x10aux::ref<Lib__closure__71>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__71> this_ = new (storage) Lib__closure__71(that_x, that_y);
        return this_;
    }
    
    Lib__closure__71(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1753-1754";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__70 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__70> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1725 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) <= (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__70* storage = x10aux::alloc<Lib__closure__70>();
        buf.record_reference(x10aux::ref<Lib__closure__70>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__70> this_ = new (storage) Lib__closure__70(that_x, that_y);
        return this_;
    }
    
    Lib__closure__70(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1724-1725";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__69 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__69> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1709 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__69* storage = x10aux::alloc<Lib__closure__69>();
        buf.record_reference(x10aux::ref<Lib__closure__69>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__69> this_ = new (storage) Lib__closure__69(that_x, that_y);
        return this_;
    }
    
    Lib__closure__69(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1708-1709";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__68 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__68> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1692 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)0)),
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__68* storage = x10aux::alloc<Lib__closure__68>();
        buf.record_reference(x10aux::ref<Lib__closure__68>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__68> this_ = new (storage) Lib__closure__68(that_x, that_y);
        return this_;
    }
    
    Lib__closure__68(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1691-1692";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__67 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__67> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1663 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) < (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__67* storage = x10aux::alloc<Lib__closure__67>();
        buf.record_reference(x10aux::ref<Lib__closure__67>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__67> this_ = new (storage) Lib__closure__67(that_x, that_y);
        return this_;
    }
    
    Lib__closure__67(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1662-1663";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__66 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__66> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1647 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__66* storage = x10aux::alloc<Lib__closure__66>();
        buf.record_reference(x10aux::ref<Lib__closure__66>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__66> this_ = new (storage) Lib__closure__66(that_x, that_y);
        return this_;
    }
    
    Lib__closure__66(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1646-1647";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__65 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__65> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1630 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)0)),
                                  x10aux::nullCheck(p)->x10::array::Point::apply(
                                    ((x10_int)1)))))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                             FMGL(array))->x10::array::Array<x10_double>::apply(
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)0)),
                                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__65* storage = x10aux::alloc<Lib__closure__65>();
        buf.record_reference(x10aux::ref<Lib__closure__65>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__65> this_ = new (storage) Lib__closure__65(that_x, that_y);
        return this_;
    }
    
    Lib__closure__65(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1629-1630";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__64 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__64> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1601 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1))))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__64* storage = x10aux::alloc<Lib__closure__64>();
        buf.record_reference(x10aux::ref<Lib__closure__64>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__64> this_ = new (storage) Lib__closure__64(that_x, that_y);
        return this_;
    }
    
    Lib__closure__64(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1600-1601";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__63 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__63> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1585 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x)), x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                               FMGL(array))->x10::array::Array<x10_double>::apply(
                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                               ((x10_int)0)),
                                                             x10aux::nullCheck(p)->x10::array::Point::apply(
                                                               ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__63* storage = x10aux::alloc<Lib__closure__63>();
        buf.record_reference(x10aux::ref<Lib__closure__63>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__63> this_ = new (storage) Lib__closure__63(that_x, that_y);
        return this_;
    }
    
    Lib__closure__63(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1584-1585";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__62 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__62> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1568 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)0)),
                                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                                          ((x10_int)1))))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__62* storage = x10aux::alloc<Lib__closure__62>();
        buf.record_reference(x10aux::ref<Lib__closure__62>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__62> this_ = new (storage) Lib__closure__62(that_x, that_y);
        return this_;
    }
    
    Lib__closure__62(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1567-1568";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__61 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__61> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1539 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                         FMGL(array))->x10::array::Array<x10_int>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1))))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_double y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__61* storage = x10aux::alloc<Lib__closure__61>();
        buf.record_reference(x10aux::ref<Lib__closure__61>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_y = buf.read<x10_double>();
        x10aux::ref<Lib__closure__61> this_ = new (storage) Lib__closure__61(that_x, that_y);
        return this_;
    }
    
    Lib__closure__61(x10aux::ref<IntMatrix> x, x10_double y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1538-1539";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__60 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__60> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x)), x10aux::nullCheck(x10aux::nullCheck(y)->
                                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)0)),
                                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__60* storage = x10aux::alloc<Lib__closure__60>();
        buf.record_reference(x10aux::ref<Lib__closure__60>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__60> this_ = new (storage) Lib__closure__60(that_x, that_y);
        return this_;
    }
    
    Lib__closure__60(x10_int x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1522-1523";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__59 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__59> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1506 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(((x10_double) (x10aux::nullCheck(x10aux::nullCheck(x)->
                                                                         FMGL(array))->x10::array::Array<x10_int>::apply(
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)0)),
                                                       x10aux::nullCheck(p)->x10::array::Point::apply(
                                                         ((x10_int)1))))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<DoubleMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__59* storage = x10aux::alloc<Lib__closure__59>();
        buf.record_reference(x10aux::ref<Lib__closure__59>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_y = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__59> this_ = new (storage) Lib__closure__59(that_x, that_y);
        return this_;
    }
    
    Lib__closure__59(x10aux::ref<IntMatrix> x, x10aux::ref<DoubleMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1505-1506";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__58 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__58> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1477 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__58* storage = x10aux::alloc<Lib__closure__58>();
        buf.record_reference(x10aux::ref<Lib__closure__58>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__58> this_ = new (storage) Lib__closure__58(that_x, that_y);
        return this_;
    }
    
    Lib__closure__58(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1476-1477";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__57 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__57> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1461 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) >= (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_int>::apply(
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)0)),
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__57* storage = x10aux::alloc<Lib__closure__57>();
        buf.record_reference(x10aux::ref<Lib__closure__57>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__57> this_ = new (storage) Lib__closure__57(that_x, that_y);
        return this_;
    }
    
    Lib__closure__57(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1460-1461";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__56 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__56> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1444 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) >= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_int>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__56* storage = x10aux::alloc<Lib__closure__56>();
        buf.record_reference(x10aux::ref<Lib__closure__56>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__56> this_ = new (storage) Lib__closure__56(that_x, that_y);
        return this_;
    }
    
    Lib__closure__56(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1443-1444";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__55 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__55> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1415 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__55* storage = x10aux::alloc<Lib__closure__55>();
        buf.record_reference(x10aux::ref<Lib__closure__55>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__55> this_ = new (storage) Lib__closure__55(that_x, that_y);
        return this_;
    }
    
    Lib__closure__55(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1414-1415";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__54 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__54> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1399 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) > (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_int>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__54* storage = x10aux::alloc<Lib__closure__54>();
        buf.record_reference(x10aux::ref<Lib__closure__54>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__54> this_ = new (storage) Lib__closure__54(that_x, that_y);
        return this_;
    }
    
    Lib__closure__54(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1398-1399";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__53 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__53> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1382 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) > (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__53* storage = x10aux::alloc<Lib__closure__53>();
        buf.record_reference(x10aux::ref<Lib__closure__53>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__53> this_ = new (storage) Lib__closure__53(that_x, that_y);
        return this_;
    }
    
    Lib__closure__53(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1381-1382";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__52 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__52> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1353 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__52* storage = x10aux::alloc<Lib__closure__52>();
        buf.record_reference(x10aux::ref<Lib__closure__52>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__52> this_ = new (storage) Lib__closure__52(that_x, that_y);
        return this_;
    }
    
    Lib__closure__52(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1352-1353";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__51 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__51> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1337 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) <= (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_int>::apply(
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)0)),
                          x10aux::nullCheck(p)->x10::array::Point::apply(
                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__51* storage = x10aux::alloc<Lib__closure__51>();
        buf.record_reference(x10aux::ref<Lib__closure__51>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__51> this_ = new (storage) Lib__closure__51(that_x, that_y);
        return this_;
    }
    
    Lib__closure__51(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1336-1337";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__50 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__50> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1320 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) <= (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_int>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__50* storage = x10aux::alloc<Lib__closure__50>();
        buf.record_reference(x10aux::ref<Lib__closure__50>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__50> this_ = new (storage) Lib__closure__50(that_x, that_y);
        return this_;
    }
    
    Lib__closure__50(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1319-1320";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__49 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__49> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1291 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__49* storage = x10aux::alloc<Lib__closure__49>();
        buf.record_reference(x10aux::ref<Lib__closure__49>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__49> this_ = new (storage) Lib__closure__49(that_x, that_y);
        return this_;
    }
    
    Lib__closure__49(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1290-1291";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__48 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__48> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1275 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x) < (x10aux::nullCheck(x10aux::nullCheck(y)->FMGL(array))->x10::array::Array<x10_int>::apply(
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)0)),
                         x10aux::nullCheck(p)->x10::array::Point::apply(
                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__48* storage = x10aux::alloc<Lib__closure__48>();
        buf.record_reference(x10aux::ref<Lib__closure__48>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__48> this_ = new (storage) Lib__closure__48(that_x, that_y);
        return this_;
    }
    
    Lib__closure__48(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1274-1275";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__47 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__47> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1258 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)0)),
                   x10aux::nullCheck(p)->x10::array::Point::apply(
                     ((x10_int)1)))) < (x10aux::nullCheck(x10aux::nullCheck(y)->
                                                            FMGL(array))->x10::array::Array<x10_int>::apply(
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)0)),
                                          x10aux::nullCheck(p)->x10::array::Point::apply(
                                            ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__47* storage = x10aux::alloc<Lib__closure__47>();
        buf.record_reference(x10aux::ref<Lib__closure__47>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__47> this_ = new (storage) Lib__closure__47(that_x, that_y);
        return this_;
    }
    
    Lib__closure__47(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1257-1258";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__46 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__46> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1229 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__46* storage = x10aux::alloc<Lib__closure__46>();
        buf.record_reference(x10aux::ref<Lib__closure__46>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__46> this_ = new (storage) Lib__closure__46(that_x, that_y);
        return this_;
    }
    
    Lib__closure__46(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1228-1229";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__45 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__45> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1213 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_int>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__45* storage = x10aux::alloc<Lib__closure__45>();
        buf.record_reference(x10aux::ref<Lib__closure__45>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__45> this_ = new (storage) Lib__closure__45(that_x, that_y);
        return this_;
    }
    
    Lib__closure__45(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1212-1213";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__44 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__44> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1196 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__44* storage = x10aux::alloc<Lib__closure__44>();
        buf.record_reference(x10aux::ref<Lib__closure__44>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__44> this_ = new (storage) Lib__closure__44(that_x, that_y);
        return this_;
    }
    
    Lib__closure__44(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1195-1196";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__43 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__43> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1167 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10_int y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__43* storage = x10aux::alloc<Lib__closure__43>();
        buf.record_reference(x10aux::ref<Lib__closure__43>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_y = buf.read<x10_int>();
        x10aux::ref<Lib__closure__43> this_ = new (storage) Lib__closure__43(that_x, that_y);
        return this_;
    }
    
    Lib__closure__43(x10aux::ref<IntMatrix> x, x10_int y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1166-1167";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__42 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__42> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1151 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_int>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_int x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__42* storage = x10aux::alloc<Lib__closure__42>();
        buf.record_reference(x10aux::ref<Lib__closure__42>(storage));
        x10_int that_x = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__42> this_ = new (storage) Lib__closure__42(that_x, that_y);
        return this_;
    }
    
    Lib__closure__42(x10_int x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1150-1151";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__41 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__41> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1134 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    x10aux::ref<IntMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__41* storage = x10aux::alloc<Lib__closure__41>();
        buf.record_reference(x10aux::ref<Lib__closure__41>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_y = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__41> this_ = new (storage) Lib__closure__41(that_x, that_y);
        return this_;
    }
    
    Lib__closure__41(x10aux::ref<IntMatrix> x, x10aux::ref<IntMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1133-1134";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__40 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__40> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1101 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 1102 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 1103 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   i,
                   j)) * (b));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> a;
    x10_double b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__40* storage = x10aux::alloc<Lib__closure__40>();
        buf.record_reference(x10aux::ref<Lib__closure__40>(storage));
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_double that_b = buf.read<x10_double>();
        x10aux::ref<Lib__closure__40> this_ = new (storage) Lib__closure__40(that_a, that_b);
        return this_;
    }
    
    Lib__closure__40(x10aux::ref<DoubleMatrix> a, x10_double b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1100-1104";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__39 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__39> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1084 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 1085 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 1086 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((a) * (x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_double>::apply(
                         i,
                         j)));
        
    }
    
    // captured environment
    x10_double a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__39* storage = x10aux::alloc<Lib__closure__39>();
        buf.record_reference(x10aux::ref<Lib__closure__39>(storage));
        x10_double that_a = buf.read<x10_double>();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__39> this_ = new (storage) Lib__closure__39(that_a, that_b);
        return this_;
    }
    
    Lib__closure__39(x10_double a, x10aux::ref<DoubleMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1083-1087";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__38 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__38> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1063 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 1064 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 1065 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_double sum = ((x10_double) (((x10_int)0)));
        
        //#line 1066 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 1066 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 1066 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 1067 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((sum) + (((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                  FMGL(array))->x10::array::Array<x10_double>::apply(
                                i,
                                k)) * (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         k,
                                         j)))));
            }
        }
        
        //#line 1069 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<DoubleMatrix> a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__38* storage = x10aux::alloc<Lib__closure__38>();
        buf.record_reference(x10aux::ref<Lib__closure__38>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__38> this_ = new (storage) Lib__closure__38(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__38(x10_int columnsA, x10aux::ref<DoubleMatrix> a, x10aux::ref<DoubleMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1062-1070";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__37 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__37> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1030 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 1031 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 1032 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_double>::apply(
                   i,
                   j)) * (((x10_double) (b))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> a;
    x10_int b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__37* storage = x10aux::alloc<Lib__closure__37>();
        buf.record_reference(x10aux::ref<Lib__closure__37>(storage));
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10_int that_b = buf.read<x10_int>();
        x10aux::ref<Lib__closure__37> this_ = new (storage) Lib__closure__37(that_a, that_b);
        return this_;
    }
    
    Lib__closure__37(x10aux::ref<DoubleMatrix> a, x10_int b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1029-1033";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__36 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__36> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 1013 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 1014 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 1015 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((a) * (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                        i,
                                        j)))));
        
    }
    
    // captured environment
    x10_double a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__36* storage = x10aux::alloc<Lib__closure__36>();
        buf.record_reference(x10aux::ref<Lib__closure__36>(storage));
        x10_double that_a = buf.read<x10_double>();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__36> this_ = new (storage) Lib__closure__36(that_a, that_b);
        return this_;
    }
    
    Lib__closure__36(x10_double a, x10aux::ref<IntMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:1012-1016";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__35 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__35> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 992 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 993 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 994 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_double sum = ((x10_double) (((x10_int)0)));
        
        //#line 995 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 995 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 995 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 996 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((sum) + (((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                  FMGL(array))->x10::array::Array<x10_double>::apply(
                                i,
                                k)) * (((x10_double) (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                                                        k,
                                                        j)))))));
            }
        }
        
        //#line 998 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<DoubleMatrix> a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__35* storage = x10aux::alloc<Lib__closure__35>();
        buf.record_reference(x10aux::ref<Lib__closure__35>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__35> this_ = new (storage) Lib__closure__35(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__35(x10_int columnsA, x10aux::ref<DoubleMatrix> a, x10aux::ref<IntMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:991-999";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__34 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__34> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 959 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 960 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 961 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_int>::apply(
                                  i,
                                  j)))) * (b));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> a;
    x10_double b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__34* storage = x10aux::alloc<Lib__closure__34>();
        buf.record_reference(x10aux::ref<Lib__closure__34>(storage));
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10_double that_b = buf.read<x10_double>();
        x10aux::ref<Lib__closure__34> this_ = new (storage) Lib__closure__34(that_a, that_b);
        return this_;
    }
    
    Lib__closure__34(x10aux::ref<IntMatrix> a, x10_double b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:958-962";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__33 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__33> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 942 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 943 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 944 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((((x10_double) (a))) * (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                          i,
                                          j)));
        
    }
    
    // captured environment
    x10_int a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__33* storage = x10aux::alloc<Lib__closure__33>();
        buf.record_reference(x10aux::ref<Lib__closure__33>(storage));
        x10_int that_a = buf.read<x10_int>();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__33> this_ = new (storage) Lib__closure__33(that_a, that_b);
        return this_;
    }
    
    Lib__closure__33(x10_int a, x10aux::ref<DoubleMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:941-945";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__32 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__32> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 921 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 922 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 923 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_double sum = ((x10_double) (((x10_int)0)));
        
        //#line 924 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 924 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 924 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 925 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((sum) + (((((x10_double) (x10aux::nullCheck(x10aux::nullCheck(a)->
                                                                 FMGL(array))->x10::array::Array<x10_int>::apply(
                                               i,
                                               k)))) * (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                                            FMGL(array))->x10::array::Array<x10_double>::apply(
                                                          k,
                                                          j)))));
            }
        }
        
        //#line 927 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<IntMatrix> a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__32* storage = x10aux::alloc<Lib__closure__32>();
        buf.record_reference(x10aux::ref<Lib__closure__32>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__32> this_ = new (storage) Lib__closure__32(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__32(x10_int columnsA, x10aux::ref<IntMatrix> a, x10aux::ref<DoubleMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:920-928";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__31 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__31> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 888 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 889 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 890 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              i,
                              j)) * (b)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> a;
    x10_int b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__31* storage = x10aux::alloc<Lib__closure__31>();
        buf.record_reference(x10aux::ref<Lib__closure__31>(storage));
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10_int that_b = buf.read<x10_int>();
        x10aux::ref<Lib__closure__31> this_ = new (storage) Lib__closure__31(that_a, that_b);
        return this_;
    }
    
    Lib__closure__31(x10aux::ref<IntMatrix> a, x10_int b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:887-891";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__30 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__30> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 871 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 872 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 873 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) ((a) * (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                      FMGL(array))->x10::array::Array<x10_int>::apply(
                                    i,
                                    j))));
        
    }
    
    // captured environment
    x10_int a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__30* storage = x10aux::alloc<Lib__closure__30>();
        buf.record_reference(x10aux::ref<Lib__closure__30>(storage));
        x10_int that_a = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__30> this_ = new (storage) Lib__closure__30(that_a, that_b);
        return this_;
    }
    
    Lib__closure__30(x10_int a, x10aux::ref<IntMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:870-874";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__29 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__29> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 850 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 851 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 852 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int sum = ((x10_int)0);
        
        //#line 853 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 853 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 853 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 854 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((x10_int) ((sum) + (((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                                        FMGL(array))->x10::array::Array<x10_int>::apply(
                                                      i,
                                                      k)) * (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                                                 FMGL(array))->x10::array::Array<x10_int>::apply(
                                                               k,
                                                               j)))))));
            }
        }
        
        //#line 856 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<IntMatrix> a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__29* storage = x10aux::alloc<Lib__closure__29>();
        buf.record_reference(x10aux::ref<Lib__closure__29>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__29> this_ = new (storage) Lib__closure__29(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__29(x10_int columnsA, x10aux::ref<IntMatrix> a, x10aux::ref<IntMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:849-857";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__28 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__28> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 815 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 816 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 817 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 i,
                 j) ? (x10_double)(a) : (x10_double)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> b;
    x10_double a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__28* storage = x10aux::alloc<Lib__closure__28>();
        buf.record_reference(x10aux::ref<Lib__closure__28>(storage));
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_a = buf.read<x10_double>();
        x10aux::ref<Lib__closure__28> this_ = new (storage) Lib__closure__28(that_b, that_a);
        return this_;
    }
    
    Lib__closure__28(x10aux::ref<BooleanMatrix> b, x10_double a) : b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:814-818";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__27 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__27> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 798 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 799 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 800 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return a ? (x10_double)(x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_double>::apply(
                                  i,
                                  j)) : (x10_double)(((x10_int)0));
        
    }
    
    // captured environment
    x10_boolean a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__27* storage = x10aux::alloc<Lib__closure__27>();
        buf.record_reference(x10aux::ref<Lib__closure__27>(storage));
        x10_boolean that_a = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__27> this_ = new (storage) Lib__closure__27(that_a, that_b);
        return this_;
    }
    
    Lib__closure__27(x10_boolean a, x10aux::ref<DoubleMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:797-801";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__26 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__26> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 781 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 782 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 783 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b ? (x10_double)(x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_double>::apply(
                                  i,
                                  j)) : (x10_double)(((x10_int)0));
        
    }
    
    // captured environment
    x10_boolean b;
    x10aux::ref<DoubleMatrix> a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__26* storage = x10aux::alloc<Lib__closure__26>();
        buf.record_reference(x10aux::ref<Lib__closure__26>(storage));
        x10_boolean that_b = buf.read<x10_boolean>();
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__26> this_ = new (storage) Lib__closure__26(that_b, that_a);
        return this_;
    }
    
    Lib__closure__26(x10_boolean b, x10aux::ref<DoubleMatrix> a) : b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:780-784";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__25 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__25> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 764 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 765 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 766 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 i,
                 j) ? (x10_double)(b) : (x10_double)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> a;
    x10_double b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__25* storage = x10aux::alloc<Lib__closure__25>();
        buf.record_reference(x10aux::ref<Lib__closure__25>(storage));
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_double that_b = buf.read<x10_double>();
        x10aux::ref<Lib__closure__25> this_ = new (storage) Lib__closure__25(that_a, that_b);
        return this_;
    }
    
    Lib__closure__25(x10aux::ref<BooleanMatrix> a, x10_double b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:763-767";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__24 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__24> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 743 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 744 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 745 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_double sum = ((x10_double) (((x10_int)0)));
        
        //#line 746 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 746 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 746 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 747 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((sum) + (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              k,
                              j)
                  ? (x10_double)((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                      FMGL(array))->x10::array::Array<x10_double>::apply(
                                    i,
                                    k)))
                  : (x10_double)(((x10_int)0))));
            }
        }
        
        //#line 749 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<BooleanMatrix> b;
    x10aux::ref<DoubleMatrix> a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__24* storage = x10aux::alloc<Lib__closure__24>();
        buf.record_reference(x10aux::ref<Lib__closure__24>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_a = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__24> this_ = new (storage) Lib__closure__24(that_columnsA, that_b, that_a);
        return this_;
    }
    
    Lib__closure__24(x10_int columnsA, x10aux::ref<BooleanMatrix> b, x10aux::ref<DoubleMatrix> a) : columnsA(columnsA), b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:742-750";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__23 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__23> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 717 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 718 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 719 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_double sum = ((x10_double) (((x10_int)0)));
        
        //#line 720 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 720 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 720 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 721 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((sum) + (x10aux::nullCheck(x10aux::nullCheck(a)->
                                                FMGL(array))->x10::array::Array<x10_boolean>::apply(
                              i,
                              k)
                  ? (x10_double)((x10aux::nullCheck(x10aux::nullCheck(b)->
                                                      FMGL(array))->x10::array::Array<x10_double>::apply(
                                    k,
                                    j)))
                  : (x10_double)(((x10_int)0))));
            }
        }
        
        //#line 723 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<BooleanMatrix> a;
    x10aux::ref<DoubleMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__23* storage = x10aux::alloc<Lib__closure__23>();
        buf.record_reference(x10aux::ref<Lib__closure__23>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<DoubleMatrix> that_b = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__23> this_ = new (storage) Lib__closure__23(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__23(x10_int columnsA, x10aux::ref<BooleanMatrix> a, x10aux::ref<DoubleMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:716-724";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__22 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<Lib__closure__22> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_double apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 689 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (-(x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_double>::apply(
                    x10aux::nullCheck(p)->x10::array::Point::apply(
                      ((x10_int)0)),
                    x10aux::nullCheck(p)->x10::array::Point::apply(
                      ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__22* storage = x10aux::alloc<Lib__closure__22>();
        buf.record_reference(x10aux::ref<Lib__closure__22>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__22> this_ = new (storage) Lib__closure__22(that_x);
        return this_;
    }
    
    Lib__closure__22(x10aux::ref<DoubleMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:688-689";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__21 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__21> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 669 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1))),
                                          ((x10_double) (((x10_int)0)))))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__21* storage = x10aux::alloc<Lib__closure__21>();
        buf.record_reference(x10aux::ref<Lib__closure__21>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__21> this_ = new (storage) Lib__closure__21(that_x);
        return this_;
    }
    
    Lib__closure__21(x10aux::ref<DoubleMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:668-669";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__20 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__20> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 648 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_double>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_double) (((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<DoubleMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__20* storage = x10aux::alloc<Lib__closure__20>();
        buf.record_reference(x10aux::ref<Lib__closure__20>(storage));
        x10aux::ref<DoubleMatrix> that_x = buf.read<x10aux::ref<DoubleMatrix> >();
        x10aux::ref<Lib__closure__20> this_ = new (storage) Lib__closure__20(that_x);
        return this_;
    }
    
    Lib__closure__20(x10aux::ref<DoubleMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:647-648";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__19 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__19> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 618 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 619 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 620 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 i,
                 j) ? (x10_int)(a) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> b;
    x10_int a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__19* storage = x10aux::alloc<Lib__closure__19>();
        buf.record_reference(x10aux::ref<Lib__closure__19>(storage));
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_a = buf.read<x10_int>();
        x10aux::ref<Lib__closure__19> this_ = new (storage) Lib__closure__19(that_b, that_a);
        return this_;
    }
    
    Lib__closure__19(x10aux::ref<BooleanMatrix> b, x10_int a) : b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:617-621";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__18 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__18> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 601 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 602 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 603 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return a ? (x10_int)(x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_int>::apply(
                               i,
                               j)) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10_boolean a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__18* storage = x10aux::alloc<Lib__closure__18>();
        buf.record_reference(x10aux::ref<Lib__closure__18>(storage));
        x10_boolean that_a = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__18> this_ = new (storage) Lib__closure__18(that_a, that_b);
        return this_;
    }
    
    Lib__closure__18(x10_boolean a, x10aux::ref<IntMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:600-604";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__17 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__17> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 584 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 585 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 586 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return b ? (x10_int)(x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_int>::apply(
                               i,
                               j)) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10_boolean b;
    x10aux::ref<IntMatrix> a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__17* storage = x10aux::alloc<Lib__closure__17>();
        buf.record_reference(x10aux::ref<Lib__closure__17>(storage));
        x10_boolean that_b = buf.read<x10_boolean>();
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__17> this_ = new (storage) Lib__closure__17(that_b, that_a);
        return this_;
    }
    
    Lib__closure__17(x10_boolean b, x10aux::ref<IntMatrix> a) : b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:583-587";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__16 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__16> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 567 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 568 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 569 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 i,
                 j) ? (x10_int)(b) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> a;
    x10_int b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__16* storage = x10aux::alloc<Lib__closure__16>();
        buf.record_reference(x10aux::ref<Lib__closure__16>(storage));
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_int that_b = buf.read<x10_int>();
        x10aux::ref<Lib__closure__16> this_ = new (storage) Lib__closure__16(that_a, that_b);
        return this_;
    }
    
    Lib__closure__16(x10aux::ref<BooleanMatrix> a, x10_int b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:566-570";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__15 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__15> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 546 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 547 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 548 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int sum = ((x10_int)0);
        
        //#line 549 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 549 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 549 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 550 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((x10_int) ((sum) + (x10aux::nullCheck(x10aux::nullCheck(b)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         k,
                                         j)
                  ? (x10_int)((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                   FMGL(array))->x10::array::Array<x10_int>::apply(
                                 i,
                                 k)))
                  : (x10_int)(((x10_int)0)))));
            }
        }
        
        //#line 552 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<BooleanMatrix> b;
    x10aux::ref<IntMatrix> a;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->b);
        buf.write(this->a);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__15* storage = x10aux::alloc<Lib__closure__15>();
        buf.record_reference(x10aux::ref<Lib__closure__15>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_a = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__15> this_ = new (storage) Lib__closure__15(that_columnsA, that_b, that_a);
        return this_;
    }
    
    Lib__closure__15(x10_int columnsA, x10aux::ref<BooleanMatrix> b, x10aux::ref<IntMatrix> a) : columnsA(columnsA), b(b), a(a) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:545-553";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__14 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__14> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 520 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 521 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 522 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int sum = ((x10_int)0);
        
        //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 523 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 524 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((x10_int) ((sum) + (x10aux::nullCheck(x10aux::nullCheck(a)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         i,
                                         k)
                  ? (x10_int)((x10aux::nullCheck(x10aux::nullCheck(b)->
                                                   FMGL(array))->x10::array::Array<x10_int>::apply(
                                 k,
                                 j)))
                  : (x10_int)(((x10_int)0)))));
            }
        }
        
        //#line 526 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<BooleanMatrix> a;
    x10aux::ref<IntMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__14* storage = x10aux::alloc<Lib__closure__14>();
        buf.record_reference(x10aux::ref<Lib__closure__14>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<IntMatrix> that_b = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__14> this_ = new (storage) Lib__closure__14(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__14(x10_int columnsA, x10aux::ref<BooleanMatrix> a, x10aux::ref<IntMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:519-527";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__13 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__13> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 492 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return ((x10_int) -(x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_int>::apply(
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)0)),
                              x10aux::nullCheck(p)->x10::array::Point::apply(
                                ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__13* storage = x10aux::alloc<Lib__closure__13>();
        buf.record_reference(x10aux::ref<Lib__closure__13>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__13> this_ = new (storage) Lib__closure__13(that_x);
        return this_;
    }
    
    Lib__closure__13(x10aux::ref<IntMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:491-492";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__12 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__12> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!((!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                              FMGL(array))->x10::array::Array<x10_int>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1))),
                                          ((x10_int)0)))));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__12* storage = x10aux::alloc<Lib__closure__12>();
        buf.record_reference(x10aux::ref<Lib__closure__12>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__12> this_ = new (storage) Lib__closure__12(that_x);
        return this_;
    }
    
    Lib__closure__12(x10aux::ref<IntMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:471-472";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__11 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__11> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 451 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_int>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       ((x10_int)0)));
        
    }
    
    // captured environment
    x10aux::ref<IntMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__11* storage = x10aux::alloc<Lib__closure__11>();
        buf.record_reference(x10aux::ref<Lib__closure__11>(storage));
        x10aux::ref<IntMatrix> that_x = buf.read<x10aux::ref<IntMatrix> >();
        x10aux::ref<Lib__closure__11> this_ = new (storage) Lib__closure__11(that_x);
        return this_;
    }
    
    Lib__closure__11(x10aux::ref<IntMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:450-451";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__10 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__10> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 260 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 261 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 262 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::nullCheck(x10aux::nullCheck(a)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                  i,
                  j) && b) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> a;
    x10_boolean b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__10* storage = x10aux::alloc<Lib__closure__10>();
        buf.record_reference(x10aux::ref<Lib__closure__10>(storage));
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_b = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__10> this_ = new (storage) Lib__closure__10(that_a, that_b);
        return this_;
    }
    
    Lib__closure__10(x10aux::ref<BooleanMatrix> a, x10_boolean b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:259-263";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__9 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__9> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 243 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 244 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 245 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (a && x10aux::nullCheck(x10aux::nullCheck(b)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                       i,
                       j)) ? (x10_int)(((x10_int)1)) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10_boolean a;
    x10aux::ref<BooleanMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__9* storage = x10aux::alloc<Lib__closure__9>();
        buf.record_reference(x10aux::ref<Lib__closure__9>(storage));
        x10_boolean that_a = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__9> this_ = new (storage) Lib__closure__9(that_a, that_b);
        return this_;
    }
    
    Lib__closure__9(x10_boolean a, x10aux::ref<BooleanMatrix> b) : a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:242-246";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__8 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__8> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 223 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int i = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)0));
        
        //#line 224 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int j = x10aux::nullCheck(p)->x10::array::Point::apply(((x10_int)1));
        
        //#line 225 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
        x10_int sum = ((x10_int)0);
        
        //#line 226 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.For_c
        {
            x10_int k;
            for (
                 //#line 226 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10LocalDecl_c
                 k = ((x10_int)0); ((k) < (columnsA)); 
                                                       //#line 226 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                                                       k = ((x10_int) ((k) + (((x10_int)1)))))
            {
                
                //#line 227 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": polyglot.ast.Eval_c
                sum =
                  ((x10_int) ((sum) + ((x10aux::nullCheck(x10aux::nullCheck(a)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          i,
                                          k) &&
                                        x10aux::nullCheck(x10aux::nullCheck(b)->
                                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                          k,
                                          j))
                  ? (x10_int)(((x10_int)1))
                  : (x10_int)(((x10_int)0)))));
            }
        }
        
        //#line 229 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return sum;
        
    }
    
    // captured environment
    x10_int columnsA;
    x10aux::ref<BooleanMatrix> a;
    x10aux::ref<BooleanMatrix> b;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->columnsA);
        buf.write(this->a);
        buf.write(this->b);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__8* storage = x10aux::alloc<Lib__closure__8>();
        buf.record_reference(x10aux::ref<Lib__closure__8>(storage));
        x10_int that_columnsA = buf.read<x10_int>();
        x10aux::ref<BooleanMatrix> that_a = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_b = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__8> this_ = new (storage) Lib__closure__8(that_columnsA, that_a, that_b);
        return this_;
    }
    
    Lib__closure__8(x10_int columnsA, x10aux::ref<BooleanMatrix> a, x10aux::ref<BooleanMatrix> b) : columnsA(columnsA), a(a), b(b) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:222-230";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__7 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int>::itable<Lib__closure__7> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_int apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 196 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)0)),
                 x10aux::nullCheck(p)->x10::array::Point::apply(
                   ((x10_int)1))) ? (x10_int)(((x10_int)-1)) : (x10_int)(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__7* storage = x10aux::alloc<Lib__closure__7>();
        buf.record_reference(x10aux::ref<Lib__closure__7>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__7> this_ = new (storage) Lib__closure__7(that_x);
        return this_;
    }
    
    Lib__closure__7(x10aux::ref<BooleanMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_int> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:195-196";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__6 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__6> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 176 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!(x10aux::nullCheck(x10aux::nullCheck(x)->FMGL(array))->x10::array::Array<x10_boolean>::apply(
                    x10aux::nullCheck(p)->x10::array::Point::apply(
                      ((x10_int)0)),
                    x10aux::nullCheck(p)->x10::array::Point::apply(
                      ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__6* storage = x10aux::alloc<Lib__closure__6>();
        buf.record_reference(x10aux::ref<Lib__closure__6>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__6> this_ = new (storage) Lib__closure__6(that_x);
        return this_;
    }
    
    Lib__closure__6(x10aux::ref<BooleanMatrix> x) : x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:175-176";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__5 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__5> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 154 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__5* storage = x10aux::alloc<Lib__closure__5>();
        buf.record_reference(x10aux::ref<Lib__closure__5>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__5> this_ = new (storage) Lib__closure__5(that_x, that_y);
        return this_;
    }
    
    Lib__closure__5(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:153-154";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__4 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__4> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 138 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                              FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)0)),
                                            x10aux::nullCheck(p)->x10::array::Point::apply(
                                              ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__4* storage = x10aux::alloc<Lib__closure__4>();
        buf.record_reference(x10aux::ref<Lib__closure__4>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__4> this_ = new (storage) Lib__closure__4(that_x, that_y);
        return this_;
    }
    
    Lib__closure__4(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:137-138";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__3 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__3> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 119 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (!x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1))),
                                       x10aux::nullCheck(x10aux::nullCheck(y)->
                                                           FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)0)),
                                         x10aux::nullCheck(p)->x10::array::Point::apply(
                                           ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__3* storage = x10aux::alloc<Lib__closure__3>();
        buf.record_reference(x10aux::ref<Lib__closure__3>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__3> this_ = new (storage) Lib__closure__3(that_x, that_y);
        return this_;
    }
    
    Lib__closure__3(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:118-119";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__2 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__2> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 91 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      y));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10_boolean y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__2* storage = x10aux::alloc<Lib__closure__2>();
        buf.record_reference(x10aux::ref<Lib__closure__2>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10_boolean that_y = buf.read<x10_boolean>();
        x10aux::ref<Lib__closure__2> this_ = new (storage) Lib__closure__2(that_x, that_y);
        return this_;
    }
    
    Lib__closure__2(x10aux::ref<BooleanMatrix> x, x10_boolean y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:90-91";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__1 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__1> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 75 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x, x10aux::nullCheck(x10aux::nullCheck(y)->
                                                             FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)0)),
                                           x10aux::nullCheck(p)->x10::array::Point::apply(
                                             ((x10_int)1)))));
        
    }
    
    // captured environment
    x10_boolean x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__1* storage = x10aux::alloc<Lib__closure__1>();
        buf.record_reference(x10aux::ref<Lib__closure__1>(storage));
        x10_boolean that_x = buf.read<x10_boolean>();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__1> this_ = new (storage) Lib__closure__1(that_x, that_y);
        return this_;
    }
    
    Lib__closure__1(x10_boolean x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:74-75";
    }

};

#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
class Lib__closure__0 : public x10::lang::Closure {
    public:
    
    static x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean>::itable<Lib__closure__0> _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean apply(x10aux::ref<x10::array::Point> p) {
        
        //#line 56 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10": x10.ast.X10Return_c
        return (x10aux::struct_equals(x10aux::nullCheck(x10aux::nullCheck(x)->
                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1))),
                                      x10aux::nullCheck(x10aux::nullCheck(y)->
                                                          FMGL(array))->x10::array::Array<x10_boolean>::apply(
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)0)),
                                        x10aux::nullCheck(p)->x10::array::Point::apply(
                                          ((x10_int)1)))));
        
    }
    
    // captured environment
    x10aux::ref<BooleanMatrix> x;
    x10aux::ref<BooleanMatrix> y;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->x);
        buf.write(this->y);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        Lib__closure__0* storage = x10aux::alloc<Lib__closure__0>();
        buf.record_reference(x10aux::ref<Lib__closure__0>(storage));
        x10aux::ref<BooleanMatrix> that_x = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<BooleanMatrix> that_y = buf.read<x10aux::ref<BooleanMatrix> >();
        x10aux::ref<Lib__closure__0> this_ = new (storage) Lib__closure__0(that_x, that_y);
        return this_;
    }
    
    Lib__closure__0(x10aux::ref<BooleanMatrix> x, x10aux::ref<BooleanMatrix> y) : x(x), y(y) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/Lib.x10:55-56";
    }

};

