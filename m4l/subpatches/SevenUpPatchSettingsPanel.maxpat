{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 1113.0, 228.0, 1338.0, 1026.0 ],
		"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
		"bglocked" : 0,
		"defrect" : [ 1113.0, 228.0, 1338.0, 1026.0 ],
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
					"maxclass" : "message",
					"text" : "set none",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"patching_rect" : [ 595.0, 444.0, 57.0, 18.0 ],
					"id" : "obj-14",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "bang",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"patching_rect" : [ 389.0, 350.0, 37.0, 18.0 ],
					"id" : "obj-110",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route started",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 389.0, 327.0, 78.0, 20.0 ],
					"id" : "obj-111",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive SEVENUP_LIFECYCLE",
					"fontsize" : 12.0,
					"numinlets" : 0,
					"patching_rect" : [ 388.0, 304.0, 182.0, 20.0 ],
					"id" : "obj-112",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl slice 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"patching_rect" : [ 418.0, 516.0, 57.0, 20.0 ],
					"id" : "obj-11",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"varname" : "sevenup-patch-path[1]",
					"text" : "pattr sevenup-patch-path",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 426.0, 440.0, 144.0, 20.0 ],
					"id" : "obj-23",
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"fontname" : "Arial",
					"saved_object_attributes" : 					{
						"initial" : [ "none" ],
						"parameter_enable" : 1
					}
,
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "sevenup-patch-path[1]",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "sevenup-patch-path[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_initial" : [ "none" ],
							"parameter_type" : 3,
							"parameter_initial_enable" : 1
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "zl slice 1",
					"fontsize" : 12.0,
					"numinlets" : 2,
					"patching_rect" : [ 282.0, 426.0, 57.0, 20.0 ],
					"id" : "obj-15",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend set",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 336.0, 453.0, 74.0, 20.0 ],
					"id" : "obj-12",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "textedit",
					"varname" : "sevenup-patch-path",
					"text" : "none",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 418.0, 485.0, 325.0, 22.0 ],
					"presentation" : 1,
					"id" : "obj-1",
					"numoutlets" : 4,
					"readonly" : 1,
					"ignoreclick" : 1,
					"outlettype" : [ "", "int", "", "" ],
					"fontname" : "Arial",
					"presentation_rect" : [ 7.0, 7.0, 372.0, 47.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "To SevenUp4LiveCore",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 422.0, 689.0, 150.0, 20.0 ],
					"id" : "obj-9",
					"numoutlets" : 0,
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numinlets" : 1,
					"patching_rect" : [ 389.0, 686.0, 25.0, 25.0 ],
					"id" : "obj-6",
					"numoutlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend set",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 210.0, 499.0, 67.0, 18.0 ],
					"id" : "obj-33",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "none",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 213.0, 523.0, 150.0, 18.0 ],
					"id" : "obj-32",
					"numoutlets" : 0,
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "name none",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"patching_rect" : [ 84.0, 277.0, 151.0, 16.0 ],
					"id" : "obj-31",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend name",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 179.0, 548.0, 79.0, 18.0 ],
					"id" : "obj-29",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "strippath",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 108.0, 470.0, 53.0, 18.0 ],
					"id" : "obj-28",
					"numoutlets" : 2,
					"outlettype" : [ "", "int" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route writepatch readpatch",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 96.0, 446.0, 139.0, 18.0 ],
					"id" : "obj-21",
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "savedialog",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 48.0, 315.0, 62.0, 18.0 ],
					"id" : "obj-5",
					"numoutlets" : 3,
					"outlettype" : [ "", "", "bang" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.button",
					"varname" : "live.button[1]",
					"numinlets" : 1,
					"patching_rect" : [ 35.0, 235.0, 15.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-16",
					"parameter_enable" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 289.0, 86.0, 15.0, 15.0 ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.button",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.button[2]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "off", "on" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 2,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "conformpath pathstyle boot",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 77.0, 346.0, 143.0, 18.0 ],
					"id" : "obj-18",
					"numoutlets" : 2,
					"outlettype" : [ "", "int" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "writepatch /Developer/Projects/SevenUpLive/sevenuplive/max/src/LoopTest",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"patching_rect" : [ 27.0, 397.0, 550.0, 16.0 ],
					"id" : "obj-19",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend writepatch",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 81.0, 371.0, 103.0, 18.0 ],
					"id" : "obj-20",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Save Patch",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 56.0, 234.0, 65.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-13",
					"numoutlets" : 0,
					"fontname" : "Arial Bold",
					"presentation_rect" : [ 306.0, 86.0, 65.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Load Patch",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 47.0, 26.0, 65.0, 18.0 ],
					"presentation" : 1,
					"id" : "obj-10",
					"numoutlets" : 0,
					"fontname" : "Arial Bold",
					"presentation_rect" : [ 306.0, 67.0, 65.0, 18.0 ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.button",
					"varname" : "live.button",
					"numinlets" : 1,
					"patching_rect" : [ 31.0, 26.0, 15.0, 15.0 ],
					"presentation" : 1,
					"id" : "obj-7",
					"parameter_enable" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 289.0, 67.0, 15.0, 15.0 ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.button",
							"parameter_invisible" : 0,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.button[3]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_enum" : [ "off", "on" ],
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 1.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 2,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "conformpath pathstyle boot",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 78.0, 95.0, 143.0, 18.0 ],
					"id" : "obj-8",
					"numoutlets" : 2,
					"outlettype" : [ "", "int" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "readpatch none",
					"fontsize" : 10.0,
					"numinlets" : 2,
					"patching_rect" : [ 24.0, 188.0, 550.0, 16.0 ],
					"id" : "obj-4",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend readpatch",
					"fontsize" : 10.0,
					"numinlets" : 1,
					"patching_rect" : [ 78.0, 162.0, 101.0, 18.0 ],
					"id" : "obj-3",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"fontname" : "Arial Bold"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "opendialog",
					"fontsize" : 12.0,
					"numinlets" : 1,
					"patching_rect" : [ 34.0, 63.0, 69.0, 20.0 ],
					"id" : "obj-17",
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"fontname" : "Arial"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "live.drop",
					"varname" : "live.drop",
					"numinlets" : 1,
					"patching_rect" : [ 207.0, 26.0, 95.0, 23.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"parameter_enable" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"legend" : "Drop Patch Here",
					"presentation_rect" : [ 7.0, 62.0, 274.0, 51.0 ],
					"saved_attribute_attributes" : 					{
						"valueof" : 						{
							"parameter_shortname" : "live.drop",
							"parameter_invisible" : 1,
							"parameter_modmax" : 127.0,
							"parameter_annotation_name" : "",
							"parameter_longname" : "live.drop[1]",
							"parameter_modmin" : 0.0,
							"parameter_linknames" : 0,
							"parameter_modmode" : 0,
							"parameter_info" : "",
							"parameter_order" : 0,
							"parameter_units" : "",
							"parameter_speedlim" : 0,
							"parameter_steps" : 0,
							"parameter_exponent" : 1.0,
							"parameter_unitstyle" : 10,
							"parameter_mmax" : 127.0,
							"parameter_mmin" : 0.0,
							"parameter_type" : 4,
							"parameter_initial_enable" : 0
						}

					}

				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-11", 1 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [ 465.5, 549.0, 789.0, 549.0, 789.0, 153.0, 87.5, 153.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-11", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-15", 1 ],
					"destination" : [ "obj-12", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-12", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 1 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-33", 0 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-31", 1 ],
					"hidden" : 0,
					"midpoints" : [ 188.5, 579.0, 19.0, 579.0, 19.0, 271.0, 133.0, 264.0 ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-7", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-31", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-16", 0 ],
					"destination" : [ "obj-31", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-28", 0 ],
					"destination" : [ "obj-29", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-4", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-20", 0 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-15", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-111", 0 ],
					"destination" : [ "obj-110", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-112", 0 ],
					"destination" : [ "obj-111", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-110", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-23", 1 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-7" : [ "live.button[3]", "live.button", 0 ],
			"obj-2" : [ "live.drop[1]", "live.drop", 0 ],
			"obj-23" : [ "sevenup-patch-path[1]", "sevenup-patch-path[1]", 0 ],
			"obj-16" : [ "live.button[2]", "live.button", 0 ]
		}

	}

}
