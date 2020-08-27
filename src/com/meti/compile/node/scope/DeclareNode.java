package com.meti.compile.node.scope;

import com.meti.compile.node.Dependents;
import com.meti.compile.node.Node;
import com.meti.compile.node.NodeGroup;
import com.meti.compile.type.Type;

import java.util.function.Consumer;
import java.util.function.Function;

public class DeclareNode implements Node {
	private final String name;
	private final Type type;

	public DeclareNode(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Node acceptDependents(Consumer<Dependents> consumer) {
		applyToDependents((Function<Dependents, Void>) dependents -> {
			consumer.accept(dependents);
			return null;
		});
		return this;
	}

	@Override
	public Node copy(Dependents dependents) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T applyToGroup(Function<NodeGroup, T> mapper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T applyToDependents(Function<Dependents, T> mapper) {
		return mapper.apply(null);
	}

	@Override
	public String render() {
		return "%s;".formatted(type.render(name));
	}
}
