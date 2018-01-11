package com.cthing.cmakeed.parser.ast.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.cthing.cmakeed.parser.ast.CMakeASTNode;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor;
import com.cthing.cmakeed.parser.ast.CMakeASTVisitor.Decision;

abstract class ASTNode implements CMakeASTNode {

	protected final List<CMakeASTNode> fChildren = new ArrayList<>();
	protected Optional<CMakeASTNode> fParent = Optional.empty();

	@Override
	public Collection<CMakeASTNode> getChildren() {
		return fChildren;
	}

	@Override
	public Optional<CMakeASTNode> getParent() {
		return fParent;
	}

	@Override
	public boolean accept(CMakeASTVisitor visitor) {
		final Decision decision = Objects.requireNonNull(visitor.visitFile(this));

		if (decision == Decision.CONTINUE) {
			for (CMakeASTNode child : fChildren) {
				if (!child.accept(visitor)) {
					return false;
				}
			}
		}

		return decision != Decision.ABORT;
	}

	protected ASTNode setParent(ASTNode parent) {
		if(!(parent == this)) {
			fParent = Optional.of(parent);
			parent.add(this);
		}
		return this;
	}

	protected void add(ASTNode abstractNode) {
		fChildren.add(abstractNode);
	}

}
