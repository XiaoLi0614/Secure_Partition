#ifndef __MAPPER_H
#define __MAPPER_H

#include <x10rt17.h>

namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> class Mapper;
template <> class Mapper<void, void, void>;
template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> class Mapper   {
    public:
    RTT_H_DECLS_INTERFACE
    
    template <class I> struct itable {
        itable(x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > (I::*map) (FMGL(T1))) : map(map) {}
        x10aux::ref<x10::util::Set<x10::util::Pair<FMGL(KI), FMGL(TI)> > > (I::*map) (FMGL(T1));
    };
    
    static void _serialize(x10aux::ref<Mapper<FMGL(T1), FMGL(KI), FMGL(TI)> > this_,
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
template <> class Mapper<void, void, void> {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};
#endif // MAPPER_H

template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> class Mapper;

#ifndef MAPPER_H_NODEPS
#define MAPPER_H_NODEPS
#include <x10/util/Set.h>
#include <x10/util/Pair.h>
#ifndef MAPPER_H_GENERICS
#define MAPPER_H_GENERICS
#endif // MAPPER_H_GENERICS
#ifndef MAPPER_H_IMPLEMENTATION
#define MAPPER_H_IMPLEMENTATION
#include <Mapper.h>


#include "Mapper.inc"

template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> void Mapper<FMGL(T1), FMGL(KI), FMGL(TI)>::_instance_init() {
    _I_("Doing initialisation for class: Mapper<FMGL(T1), FMGL(KI), FMGL(TI)>");
    
}

template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> x10aux::RuntimeType Mapper<FMGL(T1), FMGL(KI), FMGL(TI)>::rtt;
template<class FMGL(T1), class FMGL(KI), class FMGL(TI)> void Mapper<FMGL(T1), FMGL(KI), FMGL(TI)>::_initRTT() {
    rtt.canonical = &rtt;
    const x10aux::RuntimeType** parents = NULL; 
    const x10aux::RuntimeType* params[3] = { x10aux::getRTT<FMGL(T1)>(), x10aux::getRTT<FMGL(KI)>(), x10aux::getRTT<FMGL(TI)>()};
    x10aux::RuntimeType::Variance variances[3] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const x10aux::RuntimeType *canonical = x10aux::getRTT<Mapper<void, void, void> >();
    const char *name = 
        x10aux::alloc_printf("Mapper[%s,%s,%s]", params[0]->name()
                             , params[1]->name()
                             , params[2]->name()
                             );
    rtt.init(canonical, name, 0, parents, 3, params, variances);
}
#endif // MAPPER_H_IMPLEMENTATION
#endif // __MAPPER_H_NODEPS
