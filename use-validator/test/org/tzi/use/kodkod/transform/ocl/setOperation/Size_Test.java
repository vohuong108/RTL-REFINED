package org.tzi.use.kodkod.transform.ocl.setOperation;

import org.junit.Test;
import org.tzi.use.kodkod.transform.ocl.OCLTest;

public class Size_Test extends OCLTest {
	@Test
	public void test1() {
		String ocl = "Set{}->size()";
		String expected = "(none = Undefined_Set) => Undefined_Set else Int[#(none)] ";
		test("test1", ocl, expected);
	}

	@Test
	public void test2() {
		String ocl = "Set{1}->size()";
		String expected = "(((Undefined_Set in (none + Int[1])) => Undefined_Set else (none + Int[1])) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Int[1])) => Undefined_Set else (none + Int[1]))] ";
		test("test2", ocl, expected);
	}

	@Test
	public void test3() {
		String ocl = "Set{1,2}->size()";
		String expected = "(((Undefined_Set in (none + Int[1] + Int[2])) => Undefined_Set else (none + Int[1] + Int[2])) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Int[1] + Int[2])) => Undefined_Set else (none + Int[1] + Int[2]))] ";
		test("test3", ocl, expected);
	}

	@Test
	public void test4() {
		String ocl = "Set{1,2,3}->size()";
		String expected = "(((Undefined_Set in (none + Int[1] + Int[2] + Int[3])) => Undefined_Set else (none + Int[1] + Int[2] + Int[3])) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Int[1] + Int[2] + Int[3])) => Undefined_Set else (none + Int[1] + Int[2] + Int[3]))] ";
		test("test4", ocl, expected);
	}

	@Test
	public void test5() {
		String ocl = "Set{Undefined,1}->size()";
		String expected = "(((Undefined_Set in (none + Undefined + Int[1])) => Undefined_Set else (none + Undefined + Int[1])) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Undefined + Int[1])) => Undefined_Set else (none + Undefined + Int[1]))] ";
		test("test5", ocl, expected);
	}

	@Test
	public void test6() {
		String ocl = "Set{Undefined,1,2}->size()";
		String expected = "(((Undefined_Set in (none + Undefined + Int[1] + Int[2])) => Undefined_Set else (none + Undefined + Int[1] + Int[2])) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Undefined + Int[1] + Int[2])) => Undefined_Set else (none + Undefined + Int[1] + Int[2]))] ";
		test("test6", ocl, expected);
	}

	@Test
	public void test7() {
		String ocl = "Set{Undefined}->size()";
		String expected = "(((Undefined_Set in (none + Undefined)) => Undefined_Set else (none + Undefined)) = Undefined_Set) => Undefined_Set else Int[#((Undefined_Set in (none + Undefined)) => Undefined_Set else (none + Undefined))]";
		test("test7", ocl, expected);
	}

	@Test
	public void test8() {
		String ocl = "ada.luckyNumbers->size()";
		String expected = "(((Person_ada = Undefined) => Undefined_Set else (Person_ada . Person_luckyNumbers)) = Undefined_Set) => Undefined_Set else Int[#((Person_ada = Undefined) => Undefined_Set else (Person_ada . Person_luckyNumbers))]";
		test("test8", ocl, expected);
	}

	@Test
	public void test9() {
		String ocl = "ada.employerA->size()";
		String expected = "(((Person_ada = Undefined) => Undefined else (Person_ada . Job_A)) = Undefined) => Int[0] else Int[1] ";
		test("test9", ocl, expected);
	}

	@Test
	public void test10() {
		String ocl = "bob.employerA->size()";
		String expected = "(((Person_bob = Undefined) => Undefined else (Person_bob . Job_A)) = Undefined) => Int[0] else Int[1] ";
		test("test10", ocl, expected);
	}

}
