# scid

reacstrap
ag-grid
ag-grid-community
xml-beautify
npm install react-promise-tracker --save

npm install react-loader-spinner --save
npm install --save history
npm install --save react-router-dom
npm install --save rxjs
npm install react-app-polyfill


Material UI

 const muiFields = () => {
        return (
            <div>
                <Grid container spacing={3}>
                    <MuiPickersUtilsProvider
                        libInstance={moment}
                        utils={MomentUtils}
                        locale="ru"
                    >
                        <Grid item xs>
                            <KeyboardDateTimePicker
                                inputVariant="outlined"
                                ampm={false}
                                label="Начальная дата"
                                value={filter.startDateTime}
                                onChange={onChangeStartDateTime}
                                onError={console.log}
                                maxDate={filter.endDateTime}
                                // disablePast
                                format="DD.MM.yyyy HH:mm"
                            />
                            <KeyboardDateTimePicker
                                inputVariant="outlined"
                                ampm={false}
                                label="Конечная дата"
                                value={filter.endDateTime}
                                onChange={onChangeEndDateTime}
                                onError={console.log}
                                // disablePast
                                format="DD.MM.yyyy HH:mm"
                            />
                        </Grid>
                    </MuiPickersUtilsProvider>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs>
                        <TextField
                            style={{ width: "100%" }}
                            id="MessageId"
                            label="ID сообщения"
                            onChange={onFilter}
                            value={filter.messageId}
                            variant="outlined"
                        />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs>
                        <TextField
                            style={{ width: "100%" }}
                            id="MessageXML"
                            label="Содержится в XML сообщения"
                            onChange={onFilter}
                            value={filter.messageId}
                            variant="outlined"
                        />
                    </Grid>
                </Grid>
                <Grid container spacing={3}>
                    <Grid item xs={6}>
                        <FormControlLabel
                            value="end"
                            control={
                                <Checkbox
                                    checked={errorOnlyChecked}
                                    onChange={onErrorOnly}
                                    color="primary"
                                />
                            }
                            label="Только с ошибкой"
                            labelPlacement="start"
                        />
                    </Grid>
                </Grid>
            </div>
        );
    };
