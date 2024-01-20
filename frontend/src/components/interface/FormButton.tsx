import React from 'react';

type FormButtonProps = {
  label: string,
  [other: string]: any
}

const FormButton: React.FC<FormButtonProps> = ({
  label,
  ...props
}) => {
  return (
    <div className="formButton">
      <button {...props}>
        {label}
      </button>
    </div>
  );
};

export default FormButton;