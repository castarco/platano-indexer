package com.bananity.util;


// Main Class
import com.bananity.util.HashBag;

// Java Utils
import java.util.ArrayList;

// Junit
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * @author Andreu Correa Casablanca
 */
@RunWith(JUnit4.class)
public class JaccardTest
{
	private ArrayList<String> abcList;
	private ArrayList<String> bcdList;
	private ArrayList<String> cdaList;
	private ArrayList<String> dabList;
	private ArrayList<String> abcdList;

	private HashBag<String> abcHB;
	private HashBag<String> bcdHB;
	private HashBag<String> cdaHB;
	private HashBag<String> dabHB;
	private HashBag<String> abcdHB;

	@Before
	public void setupJaccardTest () {
		abcList = new ArrayList<String>();
		bcdList = new ArrayList<String>();
		cdaList = new ArrayList<String>();
		dabList = new ArrayList<String>();
		abcdList = new ArrayList<String>();

		abcList.add("a"); bcdList.add("b"); cdaList.add("c"); dabList.add("d");
		abcList.add("b"); bcdList.add("c"); cdaList.add("d"); dabList.add("a");
		abcList.add("c"); bcdList.add("d"); cdaList.add("a"); dabList.add("b");

		abcdList.add("a"); abcdList.add("b"); abcdList.add("c"); abcdList.add("d");

		abcHB = new HashBag(abcList);
		bcdHB = new HashBag(bcdList);
		cdaHB = new HashBag(cdaList);
		dabHB = new HashBag(dabList);
		abcdHB = new HashBag(abcdList);
	}

	@Test
	public void test_distance_HashBag_checkEquality () {
		// Checking Equality
		Assert.assertEquals(0., abcHB.distance(abcHB), 0.);
		Assert.assertEquals(0., bcdHB.distance(bcdHB), 0.);
		Assert.assertEquals(0., cdaHB.distance(cdaHB), 0.);
		Assert.assertEquals(0., dabHB.distance(dabHB), 0.);
	}

	@Test
	public void test_distance_HashBag_checkDistanceRelations () {
		// Checking distance relations
		Assert.assertEquals(abcHB.distance(bcdHB), abcHB.distance(cdaHB), 0.);
		Assert.assertEquals(abcHB.distance(cdaHB), abcHB.distance(dabHB), 0.);
		Assert.assertEquals(bcdHB.distance(cdaHB), bcdHB.distance(dabHB), 0.);
		Assert.assertEquals(bcdHB.distance(dabHB), bcdHB.distance(abcHB), 0.);
		Assert.assertEquals(cdaHB.distance(dabHB), cdaHB.distance(abcHB), 0.);
		Assert.assertEquals(cdaHB.distance(abcHB), cdaHB.distance(bcdHB), 0.);
		Assert.assertEquals(dabHB.distance(abcHB), dabHB.distance(bcdHB), 0.);
		Assert.assertEquals(dabHB.distance(bcdHB), dabHB.distance(cdaHB), 0.);
	}

	@Test
	public void test_distance_HashBag_checkEmpty () {
		// Checking distance for empty arrays
		Assert.assertEquals(1.0, new HashBag<String>().distance(abcHB), 0.);
	}

	@Test
	public void test_distance_HashBag_checkDistanceValues () {
		Assert.assertEquals(0.5, abcHB.distance(bcdHB), 0.00001);
		Assert.assertEquals(0.5, bcdHB.distance(abcHB), 0.00001);
		Assert.assertEquals(0.25, abcdHB.distance(abcHB), 0.00001);
		Assert.assertEquals(0.25, abcHB.distance(abcdHB), 0.00001);
	}
}