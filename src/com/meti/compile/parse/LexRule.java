package com.meti.compile.parse;

import com.meti.compile.Compiler;
import com.meti.compile.node.Node;

import java.util.Optional;

public interface LexRule {
	Optional<Node> parse(String content, Compiler compiler);
}