/*
 * ParkingIQ Project.
 *
 * (c) 2011 Aditya Sawhney
 * This code may be freely used and modified for any purpose. 
 */
package edu.colorado.piq;

/**
 * Class for all the constants.
 */
public final class Const {
	
	/**
	 * Class for all the request query parameters
	 */
	public final class Param {
		
		/** Parameter LATITUDE. */
		public static final String LATITUDE = "latitude";
		
		/** Parameter LONGITUDE. */
		public static final String LONGITUDE = "longitude";
		
		/** Parameter QUARTER. */
		public static final String QUARTER = "quarter";
		
		/** Parameter LOTID */
		public static final String LOT_ID = "lotid";
	}
	
	/**
	 * Represents the Cassandra data model.
	 */
	public final class DataModel {
		
		/**
		 * 'ParkingLot' column family.
		 */
		public final class CF_ParkingLot {
			
			public static final String NAME = "ParkingLot";
			
			public final class Col {
				
				/** Type of parking lot. */
				public static final String TYPE = "type";
				
				/** The latitude of parking lot. */
				public static final String LATITUDE = "latitude";
				
				/** The longitude of parking lot. */
				public static final String LONGITUDE = "longitude";
				
				/** Zone to which parking lot belongs. */
				public static final String ZONEID = "zoneid";
			}
		}
		
		/**
		 * 'LotStatusArchive' column family.
		 */
		public final class CF_LotStatusArchive {
			public static final String NAME = "LotStatusArchive";
		}
		
		/**
		 * 'QuarterlyAnalysis' super column family.
		 */
		public final class SCF_QuarterlyAnalysis {
			public static final String NAME = "QuarterlyAnalysis";
		}
	}
}
