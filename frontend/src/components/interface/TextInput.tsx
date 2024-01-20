import React, { ReactElement } from 'react';

type TextInputProps = {
  icon: ReactElement,
  type?: string,
  [other: string]: any
}

const TextInput: React.FC<TextInputProps> = ({
  icon,
  type = "text",
  ...props
}) => {
  return (
    <div className="textInput">
      <input
        type={type}
        {...props}
      />
      <div className="iconPositioner">
        {icon}
      </div>
    </div>
  );
};

export default TextInput;