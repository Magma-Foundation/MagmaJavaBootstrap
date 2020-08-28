package com.meti.compile.process;

import com.meti.compile.node.Dependents;
import com.meti.compile.node.Node;
import com.meti.compile.node.NodeGroup;
import com.meti.compile.node.scope.DeclareNode;
import com.meti.compile.process.util.CallStack;
import com.meti.compile.process.util.TypeStack;
import com.meti.compile.type.Type;

public class DeclareProcessor implements Processor {
	private final CallStack stack;
	private final TypeStack typeStack;

	public DeclareProcessor(CallStack stack, TypeStack typeStack) {
		this.stack = stack;
		this.typeStack = typeStack;
	}

	@Override
	public boolean canProcess(NodeGroup group) {
		return NodeGroup.Declare == group;
	}

	@Override
	public Node process(Node node) {
		return node.applyToDependents(Dependents::streamFields)
				.findFirst()
				.orElseThrow()
				.apply(this::defineInStack);
	}

	public Node defineInStack(String s, Type type) {
		String newName = stack.define(s, type);
		typeStack.push(type);
		return new DeclareNode(newName, type);
	}
}