package com.meti.compile.resolve;

import com.meti.compile.type.Type;

import java.util.Optional;

public interface NameRule {
	Optional<Type> resolve(String name);
}