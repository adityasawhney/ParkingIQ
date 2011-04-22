using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Reflection;

namespace ParkingIQ.ScriptGenerator
{
    class Program
    {
        static void Main(string[] args)
        {
            GenerateParkingLotScript();
        }

        static void GenerateParkingLotScript()
        {
            const string prolog = @"/**
** ParkingIQ Cassandra script to populate ParkingLot column family.
**
** (c) 2011 Aditya Sawhney
** This code may be freely used and modified for any purpose. 
**/

/* Switch to ParkingIQ keyspace */
use ParkingIQ;

/* Populate the parking lot data set */
";
            StringBuilder builder = new StringBuilder(prolog);
            IEnumerable<string> lines = File.ReadAllLines("Resources/ParkingLotData.prn").Skip(2);
            string[] columnNames = { "type", "latitude", "longitude", "zoneid" };
            char[] delims = {' '};
            
            foreach (string line in lines)
            {
                string[] tokens = line.Split(delims, StringSplitOptions.RemoveEmptyEntries);
                for (int i = 1; i < tokens.Count(); i++ )
                {
                    string command = string.Format("set ParkingLot['{0}']['{1}'] = '{2}';", 
                        tokens[0], 
                        columnNames[i-1], 
                        tokens[i]);
                    builder.AppendLine(command);
                }
            }

            File.WriteAllText(@"Populate_ParkingLot.txt", builder.ToString());
        }
    }
}
