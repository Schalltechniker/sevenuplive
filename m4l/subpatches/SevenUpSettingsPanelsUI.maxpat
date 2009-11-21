{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 316.0, 175.0, 332.0, 143.0 ],
		"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
		"bglocked" : 0,
		"defrect" : [ 864.0, 163.0, 989.0, 1049.0 ],
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
					"maxclass" : "newobj",
					"text" : "prepend looper",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2.0, 730.0, 91.0, 20.0 ],
					"id" : "obj-6",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "print PATCH_DATA_IN",
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"patching_rect" : [ 135.0, 64.0, 135.0, 20.0 ],
					"id" : "obj-20",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route connections looper melodizer1 melodizer2 controller tilt",
					"numoutlets" : 7,
					"fontsize" : 12.0,
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"patching_rect" : [ 59.0, 89.0, 334.0, 20.0 ],
					"id" : "obj-21",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 44.0, 35.0, 25.0, 25.0 ],
					"id" : "obj-22",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 59.0, 630.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 730.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-19",
					"numinlets" : 2,
					"name" : "SevenUpMelodizerPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend melodizer2",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 5.0, 879.0, 117.0, 20.0 ],
					"id" : "obj-18",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend melodizer1",
					"numoutlets" : 1,
					"fontsize" : 12.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3.0, 805.0, 117.0, 20.0 ],
					"id" : "obj-17",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"presentation_rect" : [ 59.0, 892.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 1002.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-15",
					"numinlets" : 0,
					"name" : "SevenUpMelodizer2SettingsPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"presentation_rect" : [ 59.0, 761.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 865.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-14",
					"numinlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 59.0, 369.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 458.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-13",
					"numinlets" : 2,
					"name" : "SevenUpLooperPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "to UI",
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"presentation_rect" : [ 258.0, 1291.0, 150.0, 20.0 ],
					"patching_rect" : [ 450.0, 1150.0, 150.0, 20.0 ],
					"presentation" : 1,
					"id" : "obj-12",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "to SevenUpCore",
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"presentation_rect" : [ 57.0, 1290.0, 150.0, 20.0 ],
					"patching_rect" : [ 10.0, 1167.0, 150.0, 20.0 ],
					"presentation" : 1,
					"id" : "obj-11",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"numoutlets" : 0,
					"presentation_rect" : [ 252.0, 1250.0, 25.0, 25.0 ],
					"patching_rect" : [ 447.0, 1113.0, 25.0, 25.0 ],
					"presentation" : 1,
					"id" : "obj-10",
					"numinlets" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "print INIT_DATA_IN",
					"numoutlets" : 0,
					"fontsize" : 12.0,
					"patching_rect" : [ 509.0, 65.0, 117.0, 20.0 ],
					"id" : "obj-8",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "route connections looper melodizer1 melodizer2 controller tilt",
					"numoutlets" : 7,
					"fontsize" : 12.0,
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"patching_rect" : [ 433.0, 90.0, 334.0, 20.0 ],
					"id" : "obj-9",
					"fontname" : "Arial",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "outlet",
					"hint" : "Raw messages to SevenUp4LiveCore",
					"numoutlets" : 0,
					"presentation_rect" : [ 64.0, 1250.0, 25.0, 25.0 ],
					"patching_rect" : [ 21.0, 1132.0, 25.0, 25.0 ],
					"presentation" : 1,
					"id" : "obj-7",
					"numinlets" : 1,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 59.0, 500.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 593.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-5",
					"numinlets" : 2,
					"name" : "SevenUpMelodizerPanel.maxpat"
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"presentation_rect" : [ 59.0, 237.0, 384.0, 127.0 ],
					"patching_rect" : [ 65.0, 322.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-2",
					"embed" : 1,
					"numinlets" : 0,
					"name" : "SevenUpLooperSettingsPanel.maxpat",
					"patcher" : 					{
						"fileversion" : 1,
						"rect" : [ 59.0, 237.0, 384.0, 127.0 ],
						"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
						"bglocked" : 0,
						"defrect" : [ 59.0, 237.0, 384.0, 127.0 ],
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
						"boxes" : [ 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "To SevenUp4LiveCore",
									"numoutlets" : 0,
									"fontsize" : 12.0,
									"patching_rect" : [ 422.0, 689.0, 150.0, 20.0 ],
									"id" : "obj-9",
									"fontname" : "Arial",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "outlet",
									"numoutlets" : 0,
									"patching_rect" : [ 389.0, 686.0, 25.0, 25.0 ],
									"id" : "obj-6",
									"numinlets" : 1,
									"comment" : ""
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "prepend set",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 210.0, 499.0, 67.0, 18.0 ],
									"id" : "obj-33",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "none",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"presentation_rect" : [ 7.0, 3.0, 118.0, 18.0 ],
									"patching_rect" : [ 212.0, 528.0, 150.0, 18.0 ],
									"presentation" : 1,
									"id" : "obj-32",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "name none",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 84.0, 277.0, 151.0, 16.0 ],
									"id" : "obj-31",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "prepend name",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 179.0, 548.0, 79.0, 18.0 ],
									"id" : "obj-29",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "strippath",
									"numoutlets" : 2,
									"fontsize" : 10.0,
									"outlettype" : [ "", "int" ],
									"patching_rect" : [ 108.0, 470.0, 53.0, 18.0 ],
									"id" : "obj-28",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "route writepatch readpatch",
									"numoutlets" : 3,
									"fontsize" : 10.0,
									"outlettype" : [ "", "", "" ],
									"patching_rect" : [ 96.0, 446.0, 139.0, 18.0 ],
									"id" : "obj-21",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "savedialog",
									"numoutlets" : 3,
									"fontsize" : 10.0,
									"outlettype" : [ "", "", "bang" ],
									"patching_rect" : [ 48.0, 315.0, 62.0, 18.0 ],
									"id" : "obj-5",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "live.button",
									"varname" : "live.button[1]",
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"presentation_rect" : [ 7.0, 43.0, 15.0, 15.0 ],
									"patching_rect" : [ 35.0, 235.0, 15.0, 15.0 ],
									"presentation" : 1,
									"id" : "obj-16",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
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
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.button",
											"parameter_invisible" : 0,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.button[2]",
											"parameter_modmin" : 0.0,
											"parameter_linknames" : 0,
											"parameter_modmode" : 0,
											"parameter_info" : ""
										}

									}

								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "conformpath pathstyle boot",
									"numoutlets" : 2,
									"fontsize" : 10.0,
									"outlettype" : [ "", "int" ],
									"patching_rect" : [ 77.0, 346.0, 143.0, 18.0 ],
									"id" : "obj-18",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "writepatch /Developer/Projects/SevenUpLive/sevenuplive/max/src/LoopTest",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 27.0, 397.0, 550.0, 16.0 ],
									"id" : "obj-19",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "prepend writepatch",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 81.0, 371.0, 103.0, 18.0 ],
									"id" : "obj-20",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Save Patch",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"presentation_rect" : [ 24.0, 43.0, 65.0, 18.0 ],
									"patching_rect" : [ 56.0, 234.0, 65.0, 18.0 ],
									"presentation" : 1,
									"id" : "obj-13",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Load Patch",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"presentation_rect" : [ 24.0, 24.0, 65.0, 18.0 ],
									"patching_rect" : [ 47.0, 26.0, 65.0, 18.0 ],
									"presentation" : 1,
									"id" : "obj-10",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "live.button",
									"varname" : "live.button",
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"presentation_rect" : [ 7.0, 24.0, 15.0, 15.0 ],
									"patching_rect" : [ 31.0, 26.0, 15.0, 15.0 ],
									"presentation" : 1,
									"id" : "obj-7",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
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
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.button",
											"parameter_invisible" : 0,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.button[3]",
											"parameter_modmin" : 0.0,
											"parameter_linknames" : 0,
											"parameter_modmode" : 0,
											"parameter_info" : ""
										}

									}

								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "conformpath pathstyle boot",
									"numoutlets" : 2,
									"fontsize" : 10.0,
									"outlettype" : [ "", "int" ],
									"patching_rect" : [ 74.0, 137.0, 143.0, 18.0 ],
									"id" : "obj-8",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "readpatch /Volumes/none/",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 24.0, 188.0, 550.0, 16.0 ],
									"id" : "obj-4",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "prepend readpatch",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 78.0, 162.0, 101.0, 18.0 ],
									"id" : "obj-3",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "opendialog",
									"numoutlets" : 2,
									"fontsize" : 12.0,
									"outlettype" : [ "", "bang" ],
									"patching_rect" : [ 30.0, 105.0, 69.0, 20.0 ],
									"id" : "obj-17",
									"fontname" : "Arial",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "live.drop",
									"varname" : "live.drop",
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"presentation_rect" : [ 7.0, 62.0, 102.0, 51.0 ],
									"legend" : "Drop Patch Here",
									"patching_rect" : [ 207.0, 26.0, 95.0, 23.0 ],
									"presentation" : 1,
									"id" : "obj-2",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
											"parameter_order" : 0,
											"parameter_units" : "",
											"parameter_speedlim" : 0,
											"parameter_steps" : 0,
											"parameter_exponent" : 1.0,
											"parameter_unitstyle" : 10,
											"parameter_mmax" : 127.0,
											"parameter_mmin" : 0.0,
											"parameter_type" : 4,
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.drop",
											"parameter_invisible" : 1,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.drop[1]",
											"parameter_modmin" : 0.0,
											"parameter_linknames" : 0,
											"parameter_modmode" : 0,
											"parameter_info" : ""
										}

									}

								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"source" : [ "obj-4", 0 ],
									"destination" : [ "obj-6", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 0 ],
									"destination" : [ "obj-6", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-21", 1 ],
									"destination" : [ "obj-28", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 0 ],
									"destination" : [ "obj-21", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-21", 0 ],
									"destination" : [ "obj-28", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-4", 0 ],
									"destination" : [ "obj-21", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-33", 0 ],
									"destination" : [ "obj-32", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-28", 0 ],
									"destination" : [ "obj-33", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-29", 0 ],
									"destination" : [ "obj-31", 1 ],
									"hidden" : 0,
									"midpoints" : [ 188.5, 579.0, 19.0, 579.0, 19.0, 271.0, 133.0, 264.0 ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-7", 0 ],
									"destination" : [ "obj-17", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-16", 0 ],
									"destination" : [ "obj-5", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-31", 0 ],
									"destination" : [ "obj-5", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-16", 0 ],
									"destination" : [ "obj-31", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-28", 0 ],
									"destination" : [ "obj-29", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-5", 0 ],
									"destination" : [ "obj-18", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-17", 0 ],
									"destination" : [ "obj-8", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-8", 0 ],
									"destination" : [ "obj-3", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-2", 0 ],
									"destination" : [ "obj-8", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-3", 0 ],
									"destination" : [ "obj-4", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-3", 0 ],
									"destination" : [ "obj-4", 1 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-18", 0 ],
									"destination" : [ "obj-20", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-20", 0 ],
									"destination" : [ "obj-19", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-20", 0 ],
									"destination" : [ "obj-19", 1 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
 ]
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "thispatcher",
					"numoutlets" : 2,
					"fontsize" : 12.0,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 641.0, 199.0, 69.0, 20.0 ],
					"id" : "obj-4",
					"fontname" : "Arial",
					"numinlets" : 1,
					"save" : [ "#N", "thispatcher", ";", "#Q", "end", ";" ]
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 418.0, 36.0, 25.0, 25.0 ],
					"id" : "obj-3",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"varname" : "SevenUpConnectionSettingsPanel",
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"presentation_rect" : [ 59.0, 105.0, 386.0, 127.0 ],
					"patching_rect" : [ 65.0, 185.0, 340.0, 130.0 ],
					"args" : [  ],
					"presentation" : 1,
					"id" : "obj-1",
					"numinlets" : 2,
					"name" : "SevenUpConnectionPanel.maxpat"
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-21", 6 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 0 ],
					"destination" : [ "obj-20", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-22", 0 ],
					"destination" : [ "obj-21", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 6 ],
					"destination" : [ "obj-4", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-9", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-8", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-18", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-17", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-18", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-17", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 2 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 3 ],
					"destination" : [ "obj-19", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 2 ],
					"destination" : [ "obj-5", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 3 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 1 ],
					"destination" : [ "obj-13", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 1 ],
					"destination" : [ "obj-13", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
