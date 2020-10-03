<template>
    <v-form @submit.prevent="submit">
        <div v-for="formItem in items" :key="formItem.id">
            <div v-if="formItem.type == 'radio' && formItem.visible">
                <v-icon class="pr-2 pt-2">{{formItem.icon}}</v-icon>
                <v-label class="pr-3">{{formItem.name}}</v-label>
                <v-radio-group
                        :error-messages="errors[formItem.id]"
                        :value="formItem.value"
                        row
                        v-model="formItem.value"
                >
                    <div v-for="option in formItem.options" :key="option.id">
                        <v-radio
                                :disabled="option.disabled()"
                                :error="formItem.hasError"
                                :label="option.name"
                                :name="formItem.htmlId"
                                :value="option.value"
                                type="radio"
                                v-if="option.visible"
                        ></v-radio>
                    </div>
                </v-radio-group>
            </div>

            <v-textarea
                    :disabled="formItem.disabled()"
                    :error="formItem.hasError"
                    :error-messages="errors[formItem.id]"
                    :label="formItem.name"
                    :name="formItem.htmlId"
                    :pattern="formItem.pattern ? formItem.pattern : false"
                    :placeholder="formItem.placeholder"
                    :prepend-icon="formItem.icon"
                    :required="formItem.required"
                    :type="formItem.type"
                    v-else-if="formItem.type == 'textarea' && formItem.visible"
                    v-model.trim="formItem.convertedValue"
            ></v-textarea>
            <v-text-field
                    :disabled="formItem.disabled()"
                    :error="formItem.hasError"
                    :error-messages="errors[formItem.id]"
                    :id="formItem.htmlId"
                    :label="formItem.name"
                    :pattern="formItem.pattern ? formItem.pattern : false"
                    :placeholder="formItem.placeholder"
                    :prepend-icon="formItem.icon"
                    :required="formItem.required"
                    :type="formItem.type"
                    v-else-if="formItem.visible"
                    v-model.trim="formItem.convertedValue"
            ></v-text-field>
            <span :delete="delete errors[formItem.id]"></span>
        </div>

        <div>
            <slot></slot>
        </div>
        <div>
            <div :v-if="hasError" :key="e" class="red--text pt-3" v-for="e in errors">
                <label>{{e}}</label>
            </div>
        </div>
    </v-form>
</template>

<script lang="ts">
    import { Component, Prop, Vue } from 'vue-property-decorator';
    import FormItem from '@/infrastructure/form/formItem';
    import ErrorHandler from '@/infrastructure/errorHandler';

    @Component
    export default class InputForm extends Vue {

        @Prop()
        readonly formItems!: FormItem[];

        @Prop()
        readonly afterValidation!: () => void;

        private hasError: boolean = false;

        private errors: any = [];

        get items(): FormItem[] {
            return this.formItems || [];
        }

        public mounted() {
            ErrorHandler.define(this.caughtErrorEvent);
        }

        public caughtErrorEvent(response: any) {
            this.errors = [];
            let responseData = response.data;
            if (responseData.errorInfoList) {
                let errorInfoList = responseData.errorInfoList;
                for (let e in errorInfoList) {
                    let fieldList = errorInfoList[e].fieldNameList;
                    let message = errorInfoList[e].errorMessage;
                    if (fieldList != null && this.formItems) {
                        for (let f in fieldList) {
                            this.errors[fieldList[f]] = message;
                        }
                    } else {
                        this.errors[0] = message;
                    }
                }
            } else if (responseData.message) {
                this.hasError = true;
                this.errors[0] = responseData.message;
            }
        }

        public submit() {
            this.hasError = false;
            this.errors = [];
            if (this.afterValidation) {
                this.afterValidation();
            }
        }
    }
</script>