{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 732.0, 574.0, 688.0, 662.0 ],
		"bglocked" : 0,
		"defrect" : [ 732.0, 574.0, 688.0, 662.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 0,
		"gridsize" : [ 4.0, 4.0 ],
		"gridsnaponopen" : 0,
		"toolbarvisible" : 1,
		"boxanimatetime" : 200,
		"imprint" : 0,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"boxes" : [ 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Controls pushing and pulling our 1 active set of looper settings from the application. Allows racks to be turned on and off and push their local settings at the same time.",
					"linecount" : 5,
					"id" : "obj-1",
					"fontname" : "Arial",
					"patching_rect" : [ 230.0, 33.0, 213.0, 75.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route 0 1",
					"outlettype" : [ "", "", "" ],
					"id" : "obj-91",
					"fontname" : "Arial",
					"patching_rect" : [ 76.0, 100.0, 58.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 3
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "bang",
					"outlettype" : [ "" ],
					"id" : "obj-81",
					"fontname" : "Arial",
					"patching_rect" : [ 209.0, 258.0, 37.0, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Push Settings",
					"id" : "obj-80",
					"fontname" : "Arial",
					"patching_rect" : [ 253.0, 285.0, 150.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"outlettype" : [ "bang" ],
					"id" : "obj-79",
					"patching_rect" : [ 231.0, 284.0, 20.0, 20.0 ],
					"numinlets" : 1,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 0",
					"outlettype" : [ "int" ],
					"id" : "obj-77",
					"fontname" : "Arial",
					"patching_rect" : [ 150.0, 101.0, 35.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend ignoreclick",
					"outlettype" : [ "" ],
					"id" : "obj-76",
					"fontname" : "Arial",
					"patching_rect" : [ 150.0, 126.0, 115.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "pushes settings to core",
					"id" : "obj-75",
					"fontname" : "Arial",
					"patching_rect" : [ 230.0, 314.0, 154.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend ui ignoreclick",
					"outlettype" : [ "" ],
					"id" : "obj-74",
					"fontname" : "Arial",
					"patching_rect" : [ 273.0, 257.0, 127.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend ui active",
					"outlettype" : [ "" ],
					"id" : "obj-73",
					"fontname" : "Arial",
					"patching_rect" : [ 273.0, 209.0, 102.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 0",
					"outlettype" : [ "int" ],
					"id" : "obj-78",
					"fontname" : "Arial",
					"patching_rect" : [ 273.0, 236.0, 35.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "started",
					"outlettype" : [ "" ],
					"id" : "obj-71",
					"fontname" : "Arial",
					"patching_rect" : [ 176.0, 311.0, 47.0, 18.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"outlettype" : [ "" ],
					"id" : "obj-70",
					"fontname" : "Arial",
					"patching_rect" : [ 176.0, 286.0, 44.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Master/Slave designates whether we are in control or what's set in the main application and patch.",
					"linecount" : 3,
					"id" : "obj-69",
					"fontname" : "Arial",
					"patching_rect" : [ 228.0, 159.0, 204.0, 48.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "This entire section is optional for writing your own looper device and is here to allow us to change and push looper parameters from the device itself.",
					"linecount" : 4,
					"id" : "obj-68",
					"fontname" : "Arial",
					"patching_rect" : [ 199.0, 559.0, 224.0, 62.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "== 0",
					"outlettype" : [ "int" ],
					"id" : "obj-67",
					"fontname" : "Arial",
					"patching_rect" : [ 85.0, 258.0, 35.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"outlettype" : [ "" ],
					"id" : "obj-65",
					"fontname" : "Arial",
					"patching_rect" : [ 118.0, 572.0, 44.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "gate 1",
					"outlettype" : [ "" ],
					"id" : "obj-64",
					"fontname" : "Arial",
					"patching_rect" : [ 67.0, 286.0, 44.0, 20.0 ],
					"numinlets" : 2,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.text",
					"varname" : "masterslave",
					"presentation_rect" : [ 395.25, 59.0, 59.5, 15.0 ],
					"outlettype" : [ "", "" ],
					"id" : "obj-60",
					"patching_rect" : [ 153.0, 160.0, 68.0, 16.0 ],
					"text" : "SLAVE",
					"presentation" : 1,
					"texton" : "MASTER",
					"parameter_enable" : 1,
					"automation" : "SLAVE",
					"numinlets" : 1,
					"automationon" : "MASTER",
					"numoutlets" : 2,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.text",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "masterslave",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 1,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "SLAVE", "MASTER" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ 0.0 ],
							"parameter_type" : 2,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send SEVENUP_CORE",
					"id" : "obj-59",
					"fontname" : "Arial",
					"patching_rect" : [ 55.0, 600.0, 139.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive SEVENUP_PATCHDATA",
					"outlettype" : [ "" ],
					"id" : "obj-55",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 50.0, 232.0, 163.0, 18.0 ],
					"numinlets" : 0,
					"fontsize" : 10.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive SEVENUP_INITDATA",
					"outlettype" : [ "" ],
					"id" : "obj-56",
					"fontname" : "Arial Bold",
					"patching_rect" : [ 410.0, 289.0, 148.0, 18.0 ],
					"numinlets" : 0,
					"fontsize" : 10.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend looper",
					"outlettype" : [ "" ],
					"id" : "obj-54",
					"fontname" : "Arial",
					"patching_rect" : [ 56.0, 535.0, 91.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route looper",
					"outlettype" : [ "", "" ],
					"id" : "obj-51",
					"fontname" : "Arial",
					"patching_rect" : [ 50.0, 315.0, 75.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route looper",
					"outlettype" : [ "", "" ],
					"id" : "obj-52",
					"fontname" : "Arial",
					"patching_rect" : [ 409.0, 317.0, 75.0, 20.0 ],
					"numinlets" : 1,
					"fontsize" : 12.0,
					"numoutlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"presentation_rect" : [ 92.0, 50.0, 384.0, 127.0 ],
					"outlettype" : [ "" ],
					"id" : "obj-47",
					"patching_rect" : [ 55.0, 360.0, 372.0, 130.0 ],
					"presentation" : 1,
					"name" : "SevenUpLooperPanel.maxpat",
					"numinlets" : 2,
					"args" : [  ],
					"numoutlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"outlettype" : [ "" ],
					"id" : "obj-94",
					"patching_rect" : [ 163.75, 40.0, 25.0, 25.0 ],
					"numinlets" : 0,
					"numoutlets" : 1,
					"comment" : ""
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-78", 0 ],
					"destination" : [ "obj-74", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-78", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-73", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-70", 0 ],
					"destination" : [ "obj-71", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-64", 0 ],
					"destination" : [ "obj-51", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-55", 0 ],
					"destination" : [ "obj-64", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-67", 0 ],
					"destination" : [ "obj-64", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-67", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-60", 0 ],
					"destination" : [ "obj-65", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-65", 0 ],
					"destination" : [ "obj-59", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-54", 0 ],
					"destination" : [ "obj-65", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-56", 0 ],
					"destination" : [ "obj-52", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-77", 0 ],
					"destination" : [ "obj-76", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-76", 0 ],
					"destination" : [ "obj-60", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-79", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-73", 0 ],
					"destination" : [ "obj-79", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-79", 0 ],
					"destination" : [ "obj-81", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-81", 0 ],
					"destination" : [ "obj-70", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-91", 1 ],
					"destination" : [ "obj-81", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-74", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-73", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-71", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-52", 0 ],
					"destination" : [ "obj-47", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-51", 0 ],
					"destination" : [ "obj-47", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-47", 0 ],
					"destination" : [ "obj-54", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-94", 0 ],
					"destination" : [ "obj-91", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-94", 0 ],
					"destination" : [ "obj-77", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-94", 0 ],
					"destination" : [ "obj-70", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-47::obj-85" : [ "sevenup-loop-length5", "live.menu", 0 ],
			"obj-47::obj-21" : [ "sevenup-loop-mode6", "live.menu", 0 ],
			"obj-47::obj-2" : [ "sevenup-loop-choke4", "live.menu", 0 ],
			"obj-47::obj-83" : [ "sevenup-loop-length7", "live.menu", 0 ],
			"obj-47::obj-9" : [ "sevenup-loop-length2", "live.menu", 0 ],
			"obj-47::obj-37" : [ "sevenup-loop-choke7", "live.menu", 0 ],
			"obj-47::obj-13" : [ "sevenup-loop-mode3", "live.menu", 0 ],
			"obj-47::obj-40" : [ "sevenup-loop-choke3", "live.menu", 0 ],
			"obj-47::obj-77" : [ "sevenup-loop-length4", "live.menu", 0 ],
			"obj-47::obj-33" : [ "sevenup-loop-choke6", "live.menu", 0 ],
			"obj-47::obj-18" : [ "sevenup-loop-mode2", "live.menu", 0 ],
			"obj-47::obj-6" : [ "sevenup-gate-loops", "live.menu", 0 ],
			"obj-47::obj-84" : [ "sevenup-loop-length6", "live.menu", 0 ],
			"obj-47::obj-8" : [ "sevenup-loop-length1", "live.menu", 0 ],
			"obj-47::obj-20" : [ "sevenup-loop-mode7", "live.menu", 0 ],
			"obj-47::obj-22" : [ "sevenup-loop-mode5", "live.menu", 0 ],
			"obj-47::obj-19" : [ "sevenup-loop-mode1", "live.menu", 0 ],
			"obj-47::obj-36" : [ "sevenup-loop-choke2", "live.menu", 0 ],
			"obj-47::obj-53" : [ "sevenup-loop-length3", "live.menu", 0 ],
			"obj-60" : [ "masterslave", "live.text", 0 ],
			"obj-47::obj-3" : [ "sevenup-loop-mode4", "live.menu", 0 ],
			"obj-47::obj-32" : [ "sevenup-loop-choke5", "live.menu", 0 ],
			"obj-47::obj-11" : [ "sevenup-loop-choke1", "live.menu", 0 ]
		}

	}

}
