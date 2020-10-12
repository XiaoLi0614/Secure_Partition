#ifndef __REDUCER_H
#define __REDUCER_H

#include <x10rt17.h>

namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
template<class FMGL(TI), class FMGL(T2)> class Reducer;
template <> class Reducer<void, void>;
template<class FMGL(TI), class FMGL(T2)> class Reducer   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(FMGL(T2) (I::*reduce) (x10aux::ref<x10::util::Set<FMGL(TI)> >)) : reduce(reduce) {}
        FMGL(T2) (I::*reduce) (x10aux::ref<x10::util::Set<FMGL(TI)> >);
    };
    
    static void _serialize(x10aux::ref<Reducer<FMGL(TI), FMGL(T2)> > this_,
                           x10aux::serialization_buffer& buf,
                           x10aux::addr_map& m) {
        x10::lang::Object::_serialize(this_, buf, m);
    }
    
    public: template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer& buf) {
        return x10::lang::Object::_deserialize<__T>(buf);
    }
    
    x10_boolean equals(x10aux::ref<x10::lang::Object> that) {
        return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Object> >(this)->equals(that);
    }
    
    x10_int hashCode() {
        return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Object> >(this)->hashCode();
    }
    
    void _instance_init();
    
    
};
template <> class Reducer<void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};
#endif // REDUCER_H

template<class FMGL(TI), class FMGL(T2)> class Reducer;

#ifndef REDUCER_H_NODEPS
#define REDUCER_H_NODEPS
#include <x10/util/Set.h>
#ifndef REDUCER_H_GENERICS
#define REDUCER_H_GENERICS
#endif // REDUCER_H_GENERICS
#ifndef REDUCER_H_IMPLEMENTATION
#define REDUCER_H_IMPLEMENTATION
#include <Reducer.h>


#include "Reducer.inc"

template<class FMGL(TI), class FMGL(T2)> void Reducer<FMGL(TI), FMGL(T2)>::_instance_init() {
    _I_("Doing initialisation for class: Reducer<FMGL(TI), FMGL(T2)>");
    
}

template<class FMGL(TI), class FMGL(T2)> x10aux::RuntimeType Reducer<FMGL(TI), FMGL(T2)>::rtt;
template<class FMGL(TI), class FMGL(T2)> void Reducer<FMGL(TI), FMGL(T2)>::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType** parents = NULL; 
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(TI)>(), x10aux::getRTT<FMGL(T2)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const x10aux::RuntimeType *canonical = x10aux::getRTT<Reducer<void, void> >();
    const char *name = 
        x10aux::alloc_printf("Reducer[%s,%s]", params[0]->name()
                             , params[1]->name()
                             );
    rtt.init(canonical, name, 0, parents, 2, params, variances);
}
#endif // REDUCER_H_IMPLEMENTATION
#endif // __REDUCER_H_NODEPS
