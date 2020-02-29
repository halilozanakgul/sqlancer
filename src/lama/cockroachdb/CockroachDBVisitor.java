package lama.cockroachdb;

import lama.cockroachdb.ast.CockroachDBAggregate;
import lama.cockroachdb.ast.CockroachDBBetweenOperation;
import lama.cockroachdb.ast.CockroachDBCaseOperation;
import lama.cockroachdb.ast.CockroachDBColumnReference;
import lama.cockroachdb.ast.CockroachDBConstant;
import lama.cockroachdb.ast.CockroachDBExpression;
import lama.cockroachdb.ast.CockroachDBFunctionCall;
import lama.cockroachdb.ast.CockroachDBInOperation;
import lama.cockroachdb.ast.CockroachDBJoin;
import lama.cockroachdb.ast.CockroachDBMultiValuedComparison;
import lama.cockroachdb.ast.CockroachDBSelect;
import lama.cockroachdb.ast.CockroachDBTableReference;

public interface CockroachDBVisitor {
	
	public void visit(CockroachDBConstant c);
	
	public void visit(CockroachDBColumnReference c);
	
	public void visit(CockroachDBFunctionCall call);
	
	public void visit(CockroachDBInOperation inOp);
	
	public void visit(CockroachDBBetweenOperation op);
	
	public void visit(CockroachDBSelect select);
	
	public void visit(CockroachDBCaseOperation cases);
	
	public void visit(CockroachDBJoin join);
	
	public void visit(CockroachDBTableReference tableRef);
	
	public void visit(CockroachDBAggregate aggr);
	
	public void visit(CockroachDBMultiValuedComparison comp);
	
	public default void visit(CockroachDBExpression expr) {
		if (expr instanceof CockroachDBConstant) {
			visit((CockroachDBConstant) expr);
		} else if (expr instanceof CockroachDBColumnReference) {
			visit((CockroachDBColumnReference) expr);
		} else if (expr instanceof CockroachDBFunctionCall) {
			visit((CockroachDBFunctionCall) expr);
		} else if (expr instanceof CockroachDBInOperation) {
			visit((CockroachDBInOperation) expr);
		} else if (expr instanceof CockroachDBBetweenOperation) {
			visit((CockroachDBBetweenOperation) expr);
		} else if (expr instanceof CockroachDBSelect) {
			visit((CockroachDBSelect) expr);
		} else if (expr instanceof CockroachDBCaseOperation) {
			visit((CockroachDBCaseOperation) expr);
		} else if (expr instanceof CockroachDBJoin) {
			visit((CockroachDBJoin) expr);
		} else if (expr instanceof CockroachDBTableReference) {
			visit((CockroachDBTableReference) expr);
		} else if (expr instanceof CockroachDBAggregate) {
			visit((CockroachDBAggregate) expr);
		} else if (expr instanceof CockroachDBMultiValuedComparison) {
			visit((CockroachDBMultiValuedComparison) expr);
		}
		
		else {
			throw new AssertionError(expr.getClass());
		}
	}
	
	public static String asString(CockroachDBExpression expr) {
		CockroachDBToStringVisitor v = new CockroachDBToStringVisitor();
		v.visit(expr);
		return v.getString();
	}

}