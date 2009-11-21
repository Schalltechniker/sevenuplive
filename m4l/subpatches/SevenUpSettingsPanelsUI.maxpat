{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 534.0, 44.0, 983.0, 1285.0 ],
		"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
		"bglocked" : 0,
		"defrect" : [ 534.0, 44.0, 983.0, 1285.0 ],
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
					"fontsize" : 12.0,
					"numoutlets" : 1,
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
					"fontsize" : 12.0,
					"numoutlets" : 0,
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
					"fontsize" : 12.0,
					"numoutlets" : 7,
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
					"presentation_rect" : [ 59.0, 630.0, 384.0, 127.0 ],
					"outlettype" : [ "" ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 730.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-19",
					"name" : "SevenUpMelodizer2Panel.maxpat",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "prepend melodizer2",
					"fontsize" : 12.0,
					"numoutlets" : 1,
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
					"fontsize" : 12.0,
					"numoutlets" : 1,
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
					"args" : [  ],
					"patching_rect" : [ 65.0, 1003.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-15",
					"numinlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 0,
					"presentation_rect" : [ 59.0, 761.0, 384.0, 127.0 ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 865.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-14",
					"numinlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"presentation_rect" : [ 59.0, 369.0, 384.0, 127.0 ],
					"outlettype" : [ "" ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 458.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-13",
					"name" : "SevenUpLooperPanel.maxpat",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "to UI",
					"fontsize" : 12.0,
					"numoutlets" : 0,
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
					"fontsize" : 12.0,
					"numoutlets" : 0,
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
					"fontsize" : 12.0,
					"numoutlets" : 0,
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
					"fontsize" : 12.0,
					"numoutlets" : 7,
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
					"presentation_rect" : [ 59.0, 500.0, 384.0, 127.0 ],
					"outlettype" : [ "" ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 593.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-5",
					"name" : "SevenUpMelodizerPanel.maxpat",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "bpatcher",
					"numoutlets" : 1,
					"presentation_rect" : [ 59.0, 237.0, 384.0, 127.0 ],
					"outlettype" : [ "" ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 322.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-2",
					"embed" : 1,
					"name" : "SevenUpLooperSettingsPanel.maxpat",
					"numinlets" : 0,
					"patcher" : 					{
						"fileversion" : 1,
						"rect" : [ 65.0, 322.0, 340.0, 130.0 ],
						"bgcolor" : [ 1.0, 1.0, 1.0, 0.0 ],
						"bglocked" : 0,
						"defrect" : [ 65.0, 322.0, 340.0, 130.0 ],
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
									"fontsize" : 12.0,
									"numoutlets" : 0,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 0,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 2,
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
									"fontsize" : 10.0,
									"numoutlets" : 3,
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
									"fontsize" : 10.0,
									"numoutlets" : 3,
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
									"presentation_rect" : [ 7.0, 43.0, 15.0, 15.0 ],
									"outlettype" : [ "" ],
									"patching_rect" : [ 35.0, 235.0, 15.0, 15.0 ],
									"presentation" : 1,
									"id" : "obj-16",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
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
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.button",
											"parameter_invisible" : 0,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.button[2]",
											"parameter_modmin" : 0.0
										}

									}

								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "conformpath pathstyle boot",
									"fontsize" : 10.0,
									"numoutlets" : 2,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 0,
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
									"fontsize" : 10.0,
									"numoutlets" : 0,
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
									"presentation_rect" : [ 7.0, 24.0, 15.0, 15.0 ],
									"outlettype" : [ "" ],
									"patching_rect" : [ 31.0, 26.0, 15.0, 15.0 ],
									"presentation" : 1,
									"id" : "obj-7",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
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
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.button",
											"parameter_invisible" : 0,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.button[3]",
											"parameter_modmin" : 0.0
										}

									}

								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "conformpath pathstyle boot",
									"fontsize" : 10.0,
									"numoutlets" : 2,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 10.0,
									"numoutlets" : 1,
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
									"fontsize" : 12.0,
									"numoutlets" : 2,
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
									"presentation_rect" : [ 7.0, 62.0, 102.0, 51.0 ],
									"outlettype" : [ "" ],
									"patching_rect" : [ 207.0, 26.0, 95.0, 23.0 ],
									"presentation" : 1,
									"id" : "obj-2",
									"legend" : "Drop Patch Here",
									"parameter_enable" : 1,
									"numinlets" : 1,
									"saved_attribute_attributes" : 									{
										"valueof" : 										{
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
											"parameter_initial_enable" : 0,
											"parameter_shortname" : "live.drop",
											"parameter_invisible" : 1,
											"parameter_modmax" : 127.0,
											"parameter_annotation_name" : "",
											"parameter_longname" : "live.drop[1]",
											"parameter_modmin" : 0.0
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
					"fontsize" : 12.0,
					"numoutlets" : 2,
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
					"presentation_rect" : [ 57.0, 104.0, 386.0, 127.0 ],
					"outlettype" : [ "", "" ],
					"args" : [  ],
					"patching_rect" : [ 65.0, 185.0, 340.0, 130.0 ],
					"presentation" : 1,
					"id" : "obj-1",
					"name" : "SevenUpConnectionPanel.maxpat",
					"numinlets" : 2
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-19", 0 ],
					"destination" : [ "obj-18", 0 ],
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
					"source" : [ "obj-9", 3 ],
					"destination" : [ "obj-19", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
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
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-7", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-9", 0 ],
					"destination" : [ "obj-1", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-21", 0 ],
					"destination" : [ "obj-1", 0 ],
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
					"source" : [ "obj-9", 2 ],
					"destination" : [ "obj-5", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ],
		"parameters" : 		{
			"obj-5::obj-36" : [ "sevenup-mgroup-slot2[1]", "live.menu", 0 ],
			"obj-5::obj-2" : [ "sevenup-mgroup-slot4[1]", "live.menu", 0 ],
			"obj-1::obj-15" : [ "sevenup-monome-ctrl", "live.menu", 0 ],
			"obj-5::obj-11" : [ "sevenup-mgroup-slot1[1]", "live.menu", 0 ],
			"obj-5::obj-20" : [ "sevenup-mmode-slot7[1]", "live.menu", 0 ],
			"obj-19::obj-18" : [ "sevenup-mmode-slot2", "live.menu", 0 ],
			"obj-13::obj-84" : [ "live.menu[5]", "live.menu", 0 ],
			"obj-13::obj-6" : [ "live.text[1]", "live.menu", 0 ],
			"obj-13::obj-85" : [ "live.menu[6]", "live.menu", 0 ],
			"obj-13::obj-37" : [ "live.menu[27]", "live.menu", 0 ],
			"obj-19::obj-2" : [ "sevenup-mgroup-slot4", "live.menu", 0 ],
			"obj-13::obj-40" : [ "live.menu[23]", "live.menu", 0 ],
			"obj-13::obj-77" : [ "live.menu[2]", "live.menu", 0 ],
			"obj-19::obj-6" : [ "sevenup-transpose-ctrl", "live.menu", 0 ],
			"obj-13::obj-53" : [ "live.menu[1]", "live.menu", 0 ],
			"obj-13::obj-33" : [ "live.menu[36]", "live.menu", 0 ],
			"obj-13::obj-9" : [ "live.menu[22]", "live.menu", 0 ],
			"obj-13::obj-36" : [ "live.menu[32]", "live.menu", 0 ],
			"obj-13::obj-32" : [ "live.menu[28]", "live.menu", 0 ],
			"obj-13::obj-8" : [ "live.menu[20]", "live.menu", 0 ],
			"obj-13::obj-11" : [ "live.menu[25]", "live.menu", 0 ],
			"obj-13::obj-20" : [ "live.menu[31]", "live.menu", 0 ],
			"obj-1::obj-37" : [ "live.grid", "live.grid", 0 ],
			"obj-5::obj-19" : [ "sevenup-mmode-slot1[1]", "live.menu", 0 ],
			"obj-13::obj-21" : [ "live.menu[33]", "live.menu", 0 ],
			"obj-5::obj-22" : [ "sevenup-mmode-slot5[1]", "live.menu", 0 ],
			"obj-2::obj-7" : [ "live.button[3]", "live.button", 0 ],
			"obj-5::obj-32" : [ "sevenup-mgroup-slot5[1]", "live.menu", 0 ],
			"obj-19::obj-3" : [ "sevenup-mmode-slot4", "live.menu", 0 ],
			"obj-5::obj-18" : [ "sevenup-mmode-slot2[1]", "live.menu", 0 ],
			"obj-19::obj-33" : [ "sevenup-mgroup-slot6", "live.menu", 0 ],
			"obj-1::obj-51" : [ "sevenup-prefix-ctrl[1]", "sevenup-prefix-ctrl[1]", 0 ],
			"obj-19::obj-5" : [ "sevenup-melodizer-tool-mode", "live.menu", 0 ],
			"obj-19::obj-9" : [ "sevenup-scale-name", "live.menu", 0 ],
			"obj-5::obj-37" : [ "sevenup-mgroup-slot7[1]", "live.menu", 0 ],
			"obj-5::obj-5" : [ "sevenup-melodizer-tool-mode[1]", "live.menu", 0 ],
			"obj-19::obj-21" : [ "sevenup-mmode-slot6", "live.menu", 0 ],
			"obj-13::obj-83" : [ "live.menu[4]", "live.menu", 0 ],
			"obj-5::obj-21" : [ "sevenup-mmode-slot6[1]", "live.menu", 0 ],
			"obj-5::obj-9" : [ "sevenup-scale-name[1]", "live.menu", 0 ],
			"obj-19::obj-19" : [ "sevenup-mmode-slot1", "live.menu", 0 ],
			"obj-19::obj-40" : [ "sevenup-mgroup-slot3", "live.menu", 0 ],
			"obj-13::obj-22" : [ "live.menu[24]", "live.menu", 0 ],
			"obj-13::obj-3" : [ "live.menu[30]", "live.menu", 0 ],
			"obj-13::obj-13" : [ "live.menu[26]", "live.menu", 0 ],
			"obj-13::obj-18" : [ "live.menu[21]", "live.menu", 0 ],
			"obj-5::obj-40" : [ "sevenup-mgroup-slot3[1]", "live.menu", 0 ],
			"obj-1::obj-14" : [ "sevenup-osc-listen-port", "live.numbox", 0 ],
			"obj-13::obj-19" : [ "live.menu[34]", "live.menu", 0 ],
			"obj-5::obj-3" : [ "sevenup-mmode-slot4[1]", "live.menu", 0 ],
			"obj-1::obj-52" : [ "sevenup-address-ctrl[1]", "sevenup-prefix-ctrl[2]", 0 ],
			"obj-5::obj-13" : [ "sevenup-mmode-slot3[1]", "live.menu", 0 ],
			"obj-19::obj-13" : [ "sevenup-mmode-slot3", "live.menu", 0 ],
			"obj-19::obj-32" : [ "sevenup-mgroup-slot5", "live.menu", 0 ],
			"obj-1::obj-4" : [ "live.toggle", "live.toggle", 0 ],
			"obj-19::obj-36" : [ "sevenup-mgroup-slot2", "live.menu", 0 ],
			"obj-5::obj-8" : [ "sevenup-record-mode[1]", "live.menu", 0 ],
			"obj-1::obj-13" : [ "sevenup-osc-host-port", "live.numbox", 0 ],
			"obj-19::obj-20" : [ "sevenup-mmode-slot7", "live.menu", 0 ],
			"obj-5::obj-6" : [ "sevenup-transpose-ctrl[1]", "live.menu", 0 ],
			"obj-19::obj-22" : [ "sevenup-mmode-slot5", "live.menu", 0 ],
			"obj-13::obj-2" : [ "live.menu[35]", "live.menu", 0 ],
			"obj-5::obj-33" : [ "sevenup-mgroup-slot6[1]", "live.menu", 0 ],
			"obj-19::obj-37" : [ "sevenup-mgroup-slot7", "live.menu", 0 ],
			"obj-2::obj-16" : [ "live.button[2]", "live.button", 0 ],
			"obj-19::obj-11" : [ "sevenup-mgroup-slot1", "live.menu", 0 ],
			"obj-19::obj-8" : [ "sevenup-record-mode", "live.menu", 0 ],
			"obj-2::obj-2" : [ "live.drop[1]", "live.drop", 0 ]
		}

	}

}
