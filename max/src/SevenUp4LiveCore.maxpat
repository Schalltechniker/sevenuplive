{
	"patcher" : 	{
		"fileversion" : 1,
		"rect" : [ 25.0, 69.0, 776.0, 631.0 ],
		"bglocked" : 0,
		"defrect" : [ 25.0, 69.0, 776.0, 631.0 ],
		"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
		"openinpresentation" : 0,
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
					"maxclass" : "inlet",
					"hint" : "Raw messages in",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 125.0, 15.0, 25.0, 25.0 ],
					"id" : "obj-13",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"hint" : "Shutdown communications",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 276.0, 17.0, 25.0, 25.0 ],
					"id" : "obj-11",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 279.0, 71.0, 20.0, 20.0 ],
					"id" : "obj-6",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 201.0, 67.0, 20.0, 20.0 ],
					"id" : "obj-5",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "inlet",
					"hint" : "Initialize and connect to the monome",
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 200.0, 16.0, 25.0, 25.0 ],
					"id" : "obj-4",
					"numinlets" : 0,
					"comment" : ""
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "comment",
					"text" : "Kickstart us when transport is started or stopped",
					"linecount" : 2,
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"patching_rect" : [ 572.0, 107.0, 150.0, 29.0 ],
					"id" : "obj-7",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "p SevenUpClock",
					"numoutlets" : 2,
					"fontsize" : 10.0,
					"outlettype" : [ "bang", "" ],
					"patching_rect" : [ 379.0, 393.0, 89.0, 18.0 ],
					"id" : "obj-43",
					"fontname" : "Arial Bold",
					"numinlets" : 1,
					"patcher" : 					{
						"fileversion" : 1,
						"rect" : [ 25.0, 69.0, 1034.0, 707.0 ],
						"bglocked" : 0,
						"defrect" : [ 25.0, 69.0, 1034.0, 707.0 ],
						"openrect" : [ 0.0, 0.0, 0.0, 0.0 ],
						"openinpresentation" : 0,
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
									"text" : "The metro runs an interval with a relative time value, so it is tied to the transport automatically.\n\nIt's here to give 7up it's heart beat",
									"linecount" : 4,
									"numoutlets" : 0,
									"fontsize" : 11.595187,
									"patching_rect" : [ 198.0, 87.0, 289.0, 60.0 ],
									"id" : "obj-10",
									"fontname" : "Arial",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Bang every 16th of a bar\n",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"patching_rect" : [ 17.0, 536.0, 138.0, 18.0 ],
									"id" : "obj-9",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "outlet",
									"numoutlets" : 0,
									"patching_rect" : [ 161.0, 533.0, 25.0, 25.0 ],
									"id" : "obj-8",
									"numinlets" : 1,
									"comment" : ""
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Clock Out",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"patching_rect" : [ 241.0, 538.0, 150.0, 18.0 ],
									"id" : "obj-5",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "C4=0 D#4=1 C7=2 F7=3 E7=4",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"patching_rect" : [ 360.0, 441.0, 150.0, 18.0 ],
									"id" : "obj-31",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "outlet",
									"numoutlets" : 0,
									"patching_rect" : [ 211.0, 533.0, 25.0, 25.0 ],
									"id" : "obj-3",
									"numinlets" : 1,
									"comment" : ""
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Bang to start",
									"numoutlets" : 0,
									"fontsize" : 12.0,
									"patching_rect" : [ 198.0, 29.0, 150.0, 20.0 ],
									"id" : "obj-2",
									"fontname" : "Arial",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "inlet",
									"numoutlets" : 1,
									"outlettype" : [ "bang" ],
									"patching_rect" : [ 168.0, 26.0, 25.0, 25.0 ],
									"id" : "obj-1",
									"numinlets" : 0,
									"comment" : ""
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "button",
									"numoutlets" : 1,
									"outlettype" : [ "bang" ],
									"patching_rect" : [ 219.0, 260.0, 20.0, 20.0 ],
									"id" : "obj-24",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "metro 64n @quantize 64n @autostart 1 @autostarttime 0.",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "bang" ],
									"patching_rect" : [ 179.0, 155.0, 282.0, 18.0 ],
									"id" : "obj-12",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "value 7UP_TICK",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 251.0, 262.0, 87.0, 18.0 ],
									"id" : "obj-11",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "3",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 321.0, 443.0, 32.5, 16.0 ],
									"id" : "obj-26",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "2",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 285.0, 443.0, 32.5, 16.0 ],
									"id" : "obj-25",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "1",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 248.0, 443.0, 32.5, 16.0 ],
									"id" : "obj-23",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "message",
									"text" : "0",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 212.0, 443.0, 32.5, 16.0 ],
									"id" : "obj-15",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "match 0",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "" ],
									"patching_rect" : [ 214.0, 237.0, 48.0, 18.0 ],
									"id" : "obj-22",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "% 120",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 214.0, 215.0, 39.0, 18.0 ],
									"id" : "obj-21",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "/ 120",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 244.0, 287.0, 33.0, 18.0 ],
									"id" : "obj-13",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "transport",
									"numoutlets" : 9,
									"fontsize" : 11.595187,
									"outlettype" : [ "int", "int", "float", "float", "float", "", "int", "float", "" ],
									"patching_rect" : [ 185.0, 179.0, 127.0, 20.0 ],
									"id" : "obj-6",
									"fontname" : "Arial",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "button",
									"numoutlets" : 1,
									"outlettype" : [ "bang" ],
									"patching_rect" : [ 202.0, 265.0, 20.0, 20.0 ],
									"id" : "obj-7",
									"numinlets" : 1
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "gate 8",
									"numoutlets" : 8,
									"fontsize" : 10.0,
									"outlettype" : [ "", "", "", "", "", "", "", "" ],
									"patching_rect" : [ 216.0, 361.0, 113.5, 18.0 ],
									"id" : "obj-19",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "+ 1",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 238.0, 335.0, 32.5, 18.0 ],
									"id" : "obj-18",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "newobj",
									"text" : "% 8",
									"numoutlets" : 1,
									"fontsize" : 10.0,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 244.0, 310.0, 32.5, 18.0 ],
									"id" : "obj-16",
									"fontname" : "Arial Bold",
									"numinlets" : 2
								}

							}
, 							{
								"box" : 								{
									"maxclass" : "comment",
									"text" : "Sixteenth of a bar",
									"numoutlets" : 0,
									"fontsize" : 10.0,
									"patching_rect" : [ 331.0, 361.0, 150.0, 18.0 ],
									"id" : "obj-17",
									"fontname" : "Arial Bold",
									"numinlets" : 1
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"source" : [ "obj-11", 0 ],
									"destination" : [ "obj-13", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-6", 7 ],
									"destination" : [ "obj-11", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-18", 0 ],
									"destination" : [ "obj-26", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-18", 0 ],
									"destination" : [ "obj-25", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 7 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 6 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 5 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 4 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 3 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 2 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 1 ],
									"destination" : [ "obj-23", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-6", 7 ],
									"destination" : [ "obj-21", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-22", 0 ],
									"destination" : [ "obj-7", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-16", 0 ],
									"destination" : [ "obj-18", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-13", 0 ],
									"destination" : [ "obj-16", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-21", 0 ],
									"destination" : [ "obj-22", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-7", 0 ],
									"destination" : [ "obj-19", 1 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-18", 0 ],
									"destination" : [ "obj-19", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-19", 0 ],
									"destination" : [ "obj-15", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-12", 0 ],
									"destination" : [ "obj-6", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-22", 0 ],
									"destination" : [ "obj-24", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-24", 0 ],
									"destination" : [ "obj-11", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-1", 0 ],
									"destination" : [ "obj-12", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-15", 0 ],
									"destination" : [ "obj-3", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-23", 0 ],
									"destination" : [ "obj-3", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-25", 0 ],
									"destination" : [ "obj-3", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-26", 0 ],
									"destination" : [ "obj-3", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
, 							{
								"patchline" : 								{
									"source" : [ "obj-7", 0 ],
									"destination" : [ "obj-8", 0 ],
									"hidden" : 0,
									"midpoints" : [  ]
								}

							}
 ]
					}
,
					"saved_object_attributes" : 					{
						"globalpatchername" : "",
						"fontface" : 0,
						"fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"fontname" : "Arial",
						"default_fontsize" : 12.0
					}

				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send SEVENUP_LOOPER",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"patching_rect" : [ 118.0, 313.0, 132.0, 18.0 ],
					"id" : "obj-42",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send SEVENUP_STEPPER",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"patching_rect" : [ 90.0, 288.0, 137.0, 18.0 ],
					"id" : "obj-27",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "loadbang",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 497.0, 107.0, 55.0, 18.0 ],
					"id" : "obj-41",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"prototypename" : "M4L.patching",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 544.0, 130.0, 18.0, 18.0 ],
					"id" : "obj-29",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "t b l",
					"numoutlets" : 2,
					"fontsize" : 10.0,
					"outlettype" : [ "bang", "" ],
					"patching_rect" : [ 555.0, 209.0, 33.0, 18.0 ],
					"id" : "obj-32",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "property is_playing",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 467.0, 265.0, 104.0, 16.0 ],
					"id" : "obj-33",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "path live_set",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 539.0, 161.0, 71.0, 16.0 ],
					"id" : "obj-36",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.path",
					"numoutlets" : 3,
					"color" : [ 0.984314, 0.819608, 0.05098, 1.0 ],
					"fontsize" : 10.0,
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 539.0, 185.0, 51.0, 18.0 ],
					"id" : "obj-39",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "live.observer",
					"numoutlets" : 2,
					"color" : [ 0.984314, 0.819608, 0.05098, 1.0 ],
					"fontsize" : 10.0,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 515.0, 305.0, 73.0, 18.0 ],
					"id" : "obj-40",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send SEVENUP_MELODIZER",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"patching_rect" : [ 41.0, 265.0, 149.0, 18.0 ],
					"id" : "obj-28",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "button",
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 505.0, 348.0, 20.0, 20.0 ],
					"id" : "obj-14",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "send 7UP_CLOCK",
					"numoutlets" : 0,
					"fontsize" : 10.0,
					"patching_rect" : [ 410.0, 493.0, 97.0, 18.0 ],
					"id" : "obj-10",
					"fontname" : "Arial Bold",
					"numinlets" : 1
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "receive 7UP_CLOCK",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 308.0, 138.0, 108.0, 18.0 ],
					"id" : "obj-8",
					"fontname" : "Arial Bold",
					"numinlets" : 0
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "shutdown",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 280.0, 105.0, 58.0, 16.0 ],
					"id" : "obj-3",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "message",
					"text" : "initialize",
					"numoutlets" : 1,
					"fontsize" : 10.0,
					"outlettype" : [ "" ],
					"patching_rect" : [ 195.0, 105.0, 50.0, 16.0 ],
					"id" : "obj-2",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
, 			{
				"box" : 				{
					"maxclass" : "newobj",
					"text" : "mxj mtn.sevenuplive.max.mxj.SevenUp4Live",
					"numoutlets" : 4,
					"fontsize" : 10.0,
					"outlettype" : [ "", "", "", "" ],
					"patching_rect" : [ 165.0, 186.0, 223.0, 18.0 ],
					"id" : "obj-1",
					"fontname" : "Arial Bold",
					"numinlets" : 2
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"source" : [ "obj-2", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-3", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-8", 0 ],
					"destination" : [ "obj-1", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 0 ],
					"destination" : [ "obj-28", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 1 ],
					"destination" : [ "obj-27", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-1", 2 ],
					"destination" : [ "obj-42", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-40", 0 ],
					"destination" : [ "obj-14", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-41", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-29", 0 ],
					"destination" : [ "obj-36", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-32", 1 ],
					"destination" : [ "obj-40", 1 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-32", 0 ],
					"destination" : [ "obj-33", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-39", 1 ],
					"destination" : [ "obj-32", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-33", 0 ],
					"destination" : [ "obj-40", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-36", 0 ],
					"destination" : [ "obj-39", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-14", 0 ],
					"destination" : [ "obj-43", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-43", 1 ],
					"destination" : [ "obj-10", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-4", 0 ],
					"destination" : [ "obj-5", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-5", 0 ],
					"destination" : [ "obj-2", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-11", 0 ],
					"destination" : [ "obj-6", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-6", 0 ],
					"destination" : [ "obj-3", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
, 			{
				"patchline" : 				{
					"source" : [ "obj-13", 0 ],
					"destination" : [ "obj-1", 0 ],
					"hidden" : 0,
					"midpoints" : [  ]
				}

			}
 ]
	}

}
